package org.ipan.payment.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.ipan.common.domain.PaymentStatus;
import org.ipan.payment.domain.event.PaymentCancelledEvent;
import org.ipan.payment.domain.event.PaymentCompletedEvent;
import org.ipan.payment.domain.event.PaymentEvent;
import org.ipan.payment.domain.event.PaymentFailedEvent;
import org.ipan.payment.domain.exception.PaymentDomainException;
import org.ipan.payment.domain.model.CreditEntry;
import org.ipan.payment.domain.model.CreditHistory;
import org.ipan.payment.domain.model.Payment;
import org.ipan.payment.domain.model.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Override
    public PaymentEvent validateAndInitiatePayment(Payment payment,
                                                   CreditEntry creditEntry,
                                                   List<CreditHistory> creditHistories,
                                                   List<String> failureMessages) {
        payment.validatePayment(failureMessages);
        payment.initializePayment();
        validateCreditEntry(payment, creditEntry, failureMessages);
        subtractCreditEntry(payment, creditEntry);
        updateCreditHistory(payment, creditHistories, TransactionType.DEBIT);
        validateCreditHistory(creditEntry, creditHistories, failureMessages);

        if (failureMessages.isEmpty()) {
            log.info("Payment is initiated for order id: {}", payment.getOrderId());
            payment.updateStatus(PaymentStatus.COMPLETED);
            return new PaymentCompletedEvent(payment, ZonedDateTime.now());
        } else {
            log.info("Payment initiation is failed for order id: {}", payment.getOrderId());
            payment.updateStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(), failureMessages);
        }
    }

    @Override
    public PaymentEvent validateAndCancelPayment(Payment payment,
                                                 CreditEntry creditEntry,
                                                 List<CreditHistory> creditHistories,
                                                 List<String> failureMessages) {
        payment.validatePayment(failureMessages);
        addCreditEntry(payment, creditEntry);
        updateCreditHistory(payment, creditHistories, TransactionType.CREDIT);

        if (failureMessages.isEmpty()) {
            log.info("Payment is cancelled for order id: {}", payment.getOrderId());
            payment.updateStatus(PaymentStatus.CANCELLED);
            return new PaymentCancelledEvent(payment, ZonedDateTime.now());
        } else {
            log.info("Payment cancellation is failed for order id: {}", payment.getOrderId());
            payment.updateStatus(PaymentStatus.FAILED);
            return new PaymentFailedEvent(payment, ZonedDateTime.now(), failureMessages);
        }
    }

    private void validateCreditEntry(Payment payment, CreditEntry creditEntry, List<String> failureMessages) {
        if (payment.getPrice().compareTo(creditEntry.getTotalCreditAmount()) >= 1) {
            log.error("Customer with id: {} doesn't have enough credit for payment!", payment.getCustomerId());
            failureMessages.add("Customer with id=" + payment.getCustomerId() + " doesn't have enough credit for payment!");
            throw new PaymentDomainException("Customer with id=" + payment.getCustomerId() + " doesn't have enough credit for payment!");
        }
    }

    private void subtractCreditEntry(Payment payment, CreditEntry creditEntry) {
        creditEntry.subtractCreditAmount(payment.getPrice());
    }

    private void updateCreditHistory(Payment payment,
                                     List<CreditHistory> creditHistories,
                                     TransactionType transactionType) {
        creditHistories.add(CreditHistory.builder()
                .id(UUID.randomUUID())
                .customerId(payment.getCustomerId())
                .amount(payment.getPrice())
                .type(transactionType)
                .build());
    }

    private void validateCreditHistory(CreditEntry creditEntry,
                                       List<CreditHistory> creditHistories,
                                       List<String> failureMessages) {
        BigDecimal totalCreditHistory = getTotalHistoryAmount(creditHistories, TransactionType.CREDIT);
        BigDecimal totalDebitHistory = getTotalHistoryAmount(creditHistories, TransactionType.DEBIT);

        if (totalDebitHistory.compareTo(totalCreditHistory) >= 1) {
            log.error("Customer with id: {} doesn't have enough credit according to credit history", creditEntry.getCustomerId());
            failureMessages.add("Customer with id=" + creditEntry.getCustomerId() + " doesn't have enough credit according to credit history!");
        }

        if (!creditEntry.getTotalCreditAmount().equals(totalCreditHistory.subtract(totalDebitHistory))) {
            log.error("Credit history total is not equal to current credit for customer id: {}!",
                    creditEntry.getCustomerId());
            failureMessages.add("Credit history total is not equal to current credit for customer id: " +
                    creditEntry.getCustomerId() + "!");
        }
    }

    private BigDecimal getTotalHistoryAmount(List<CreditHistory> creditHistories, TransactionType transactionType) {
        return creditHistories.stream()
                .filter(creditHistory -> transactionType == creditHistory.getType())
                .map(CreditHistory::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void addCreditEntry(Payment payment, CreditEntry creditEntry) {
        creditEntry.addCreditAmount(payment.getPrice());
    }
}

package org.ipan.payment.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.common.dto.messaging.order.OrderCreatedMessage;
import org.ipan.payment.application.events.PaymentProcessedEventPublisher;
import org.ipan.payment.application.exception.PaymentApplicationServiceException;
import org.ipan.payment.application.mapper.PaymentMapper;
import org.ipan.payment.domain.event.PaymentEvent;
import org.ipan.payment.domain.model.CreditEntry;
import org.ipan.payment.domain.model.CreditHistory;
import org.ipan.payment.domain.model.Payment;
import org.ipan.payment.domain.repository.CreditEntryRepository;
import org.ipan.payment.domain.repository.CreditHistoryRepository;
import org.ipan.payment.domain.repository.PaymentRepository;
import org.ipan.payment.domain.service.PaymentDomainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class ProcessPaymentHandler {
    private final PaymentMapper paymentDataMapper;
    private final PaymentRepository paymentRepository;
    private final CreditEntryRepository creditEntryRepository;
    private final CreditHistoryRepository creditHistoryRepository;
    private final PaymentDomainService paymentDomainService;
    private final PaymentProcessedEventPublisher paymentProcessedEventPublisher;

    @Transactional
    public void processPayment(OrderCreatedMessage orderCreatedMessage) {
        log.info("Received payment complete event for order id: {}", orderCreatedMessage.getOrderId());
        Payment payment = paymentDataMapper.paymentRequestModelToPayment(orderCreatedMessage);
        CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
        List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
        List<String> failureMessages = new ArrayList<>();
        PaymentEvent paymentEvent = paymentDomainService.validateAndInitiatePayment(payment, creditEntry, creditHistories, failureMessages);
        persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
        paymentProcessedEventPublisher.publishPaymentEvent(paymentEvent);
    }

    private CreditEntry getCreditEntry(UUID customerId) {
        Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(customerId);
        if (creditEntry.isEmpty()) {
            log.error("Could not find credit entry for customer: {}", customerId);
            throw new PaymentApplicationServiceException("Could not find credit entry for customer: " + customerId);
        }
        return creditEntry.get();
    }

    private List<CreditHistory> getCreditHistory(UUID customerId) {
        Optional<List<CreditHistory>> creditHistories = creditHistoryRepository.findByCustomerId(customerId);
        if (creditHistories.isEmpty()) {
            log.error("Could not find credit history for customer: {}", customerId);
            throw new PaymentApplicationServiceException("Could not find credit history for customer: " + customerId);
        }
        return creditHistories.get();
    }

    private void persistDbObjects(Payment payment,
                                  CreditEntry creditEntry,
                                  List<CreditHistory> creditHistories,
                                  List<String> failureMessages) {
        paymentRepository.save(payment);
        if (failureMessages.isEmpty()) {
            creditEntryRepository.save(creditEntry);
            creditHistoryRepository.save(creditHistories.get(creditHistories.size() - 1));
        }
    }


}

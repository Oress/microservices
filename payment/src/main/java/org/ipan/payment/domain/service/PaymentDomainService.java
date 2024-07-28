package org.ipan.payment.domain.service;


import org.ipan.payment.domain.event.PaymentEvent;
import org.ipan.payment.domain.model.CreditEntry;
import org.ipan.payment.domain.model.CreditHistory;
import org.ipan.payment.domain.model.Payment;

import java.util.List;

public interface PaymentDomainService {
    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndCancelPayment(Payment payment,
                                          CreditEntry creditEntry,
                                          List<CreditHistory> creditHistories,
                                          List<String> failureMessages);
}

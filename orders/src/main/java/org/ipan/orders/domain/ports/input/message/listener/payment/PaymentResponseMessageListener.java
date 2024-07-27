package org.ipan.orders.domain.ports.input.message.listener.payment;

import org.ipan.orders.dto.payment.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentFailed(PaymentResponse paymentResponse);
}

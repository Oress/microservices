package org.ipan.orders.application.ports.input.message.listener.payment;

import org.ipan.orders.application.model.payment.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentFailed(PaymentResponse paymentResponse);
}

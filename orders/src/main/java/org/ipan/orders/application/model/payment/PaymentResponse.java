package org.ipan.orders.application.model.payment;

import lombok.Data;
import org.ipan.common.domain.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class PaymentResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String paymentId;
    private String customerId;
    private BigDecimal price;
    private Instant createdAt;
    private PaymentStatus paymentStatus;
    private List<String> failureMessages;
}

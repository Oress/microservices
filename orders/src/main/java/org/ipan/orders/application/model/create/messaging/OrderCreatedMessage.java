package org.ipan.orders.application.model.create.messaging;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderCreatedMessage {
    private UUID orderId;
    private UUID customerId;
    private UUID productId;
    private String trackingId;
}

package org.ipan.common.dto.messaging.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedMessage {
    private UUID orderId;
    private UUID customerId;
    private String trackingId;
    private BigDecimal totalPrice;
}

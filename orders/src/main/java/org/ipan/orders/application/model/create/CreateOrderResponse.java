package org.ipan.orders.application.model.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ipan.common.domain.OrderStatus;

import java.util.UUID;

@Data
public class CreateOrderResponse {
    @NotNull
    private UUID trackingId;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private String message;
}

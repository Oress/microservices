package org.ipan.orders.application.model.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.ipan.common.domain.OrderStatus;

import java.util.UUID;

@Data
@Builder
public class CreateOrderResponse {
    @NotNull
    private String trackingId;
    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private String message;
}

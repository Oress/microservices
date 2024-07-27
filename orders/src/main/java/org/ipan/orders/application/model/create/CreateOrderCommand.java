package org.ipan.orders.application.model.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderCommand {
    @NotNull
    private UUID customerId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private List<OrderItemDTO> items;
}

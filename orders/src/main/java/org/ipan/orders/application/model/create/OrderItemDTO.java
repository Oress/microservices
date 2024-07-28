package org.ipan.orders.application.model.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItemDTO {
    @NotNull
    private UUID productId;
    @NotNull
    private int quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}

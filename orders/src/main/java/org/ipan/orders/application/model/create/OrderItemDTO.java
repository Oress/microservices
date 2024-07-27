package org.ipan.orders.application.model.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    @NotNull
    private String productId;
    @NotNull
    private int quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal subtotal;
}

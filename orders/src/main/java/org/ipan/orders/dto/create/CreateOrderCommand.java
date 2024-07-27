package org.ipan.orders.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderCommand {
    @NotNull
    private String customerId;
    @NotNull
    private List<OrderItemDTO> items;
    @NotNull
    private BigDecimal price;
}

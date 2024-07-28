package org.ipan.orders.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.domain.model.Order;
import org.ipan.orders.domain.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Slf4j
public class OrderPriceCalculator {

    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public void calculateTotalPrice(Order order) {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem item : order.getItems()) {
            calculateSubtotal(item);
            sum = sum.add(item.getSubtotal());
        }
        order.setPrice(sum.setScale(2, ROUNDING_MODE));
    }

    public void calculateSubtotal(OrderItem item) {
        BigDecimal result = item.getPricePerUnit().multiply(BigDecimal.valueOf(item.getQuantity())).setScale(2, ROUNDING_MODE);
        item.setSubtotal(result);
    }
}

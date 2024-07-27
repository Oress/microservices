package org.ipan.orders.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ipan.orders.domain.exception.OrderDomainException;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order_items", schema = "order")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;

    @Column(name = "price_per_unit", precision = 10, scale = 2)
    private BigDecimal pricePerUnit; // this field is here to avoid price changes in the product

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    public void validateItem() {
        if (product == null) {
            throw new OrderDomainException("Product must be set");
        }
        if (quantity <= 0) {
            throw new OrderDomainException("Quantity must be greater than 0");
        }
        if (subtotal == null || subtotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderDomainException("Subtotal must be greater than 0");
        }

        if (subtotal.compareTo(pricePerUnit.multiply(BigDecimal.valueOf(quantity))) != 0) {
            throw new OrderDomainException("Subtotal must be equal to product price multiplied by quantity");
        }
    }
}

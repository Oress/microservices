package org.ipan.orders.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ipan.common.domain.OrderStatus;
import org.ipan.orders.domain.exception.OrderDomainException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "orders", schema = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id")
    private UUID customerId;

    private BigDecimal price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public void pay() {
        if (status != OrderStatus.PENDING) {
            throw new OrderDomainException("Order must be in PENDING state to be paid. Current state: " + status);
        }
        status = OrderStatus.PAID;
    }

    public void approve() {
        if (status != OrderStatus.PAID) {
            throw new OrderDomainException("Order must be in PAID state to be approved. Current state: " + status);
        }
        status = OrderStatus.APPROVED;
    }

    public void cancel() {
        if (status != OrderStatus.PAID && status != OrderStatus.APPROVED) {
            throw new OrderDomainException("Order must be in PAID or APPROVED state to be cancelled. Current state: " + status);
        }
        status = OrderStatus.CANCELLING;
    }

    public void confirmCancellation() {
        if (status != OrderStatus.CANCELLING) {
            throw new OrderDomainException("Order must be in CANCELLING state to be cancelled. Current state: " + status);
        }
        status = OrderStatus.CANCELLED;
    }

    public void validateOrder() {
        validateInitOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateItemsPrice() {
        if (items == null || items.isEmpty()) {
            throw new OrderDomainException("Order must have at least one item");
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem item : items) {
            item.validateItem();
            BigDecimal subtotal = item.getSubtotal();
            sum = sum.add(subtotal);
        }

        if (price.compareTo(sum) != 0) {
            throw new OrderDomainException("Order price must be equal to sum of item prices. Order price: "
                    + price + " Sum of item prices: " + sum);
        }
    }

    private void validateTotalPrice() {
        if(price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderDomainException("Order price must be greater than zero");
        }
    }

    private void validateInitOrder() {
        if (status != null || id != null) {
            throw new OrderDomainException("Order is not in correct state for initialization");
        }
    }

    public void initializeOrder() {
        status = OrderStatus.PENDING;
        trackingNumber = UUID.randomUUID().toString();
    }

    public void addItem(UUID productId, int quantity) {
        OrderItem item = new OrderItem();
        item.setOrder(this);
        item.setProductId(productId);
        item.setQuantity(quantity);
        // this will do for now
        item.setPricePerUnit(BigDecimal.valueOf(Math.random() * 100).setScale(2, RoundingMode.HALF_UP));
        items.add(item);
    }
}

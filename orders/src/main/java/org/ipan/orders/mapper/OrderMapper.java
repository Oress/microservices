package org.ipan.orders.mapper;

import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.create.CreateOrderResponse;
import org.ipan.orders.application.model.create.OrderItemDTO;
import org.ipan.orders.application.model.create.messaging.OrderCreatedMessage;
import org.ipan.orders.application.model.track.TrackOrderResponse;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.domain.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class OrderMapper {
    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .trackingId(order.getTrackingNumber())
                .orderStatus(order.getStatus())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        Order order = new Order();
        order.setTrackingNumber(UUID.randomUUID().toString());
        order.setCustomerId(createOrderCommand.getCustomerId());

        for (OrderItemDTO item : createOrderCommand.getItems()) {
            order.addItem(item.getProductId(), item.getQuantity());
        }

        return order;
    }

    public CreateOrderResponse orderCreatedEventToOrderCreatedResponse(OrderCreatedEvent orderCreatedEvent) {
        return CreateOrderResponse.builder()
                .trackingId(orderCreatedEvent.getOrder().getTrackingNumber())
                .orderStatus(orderCreatedEvent.getOrder().getStatus())
                .message("Order created successfully")
                .build();
    }

    public OrderCreatedMessage orderCreatedEventToOrderCreatedMessage(OrderCreatedEvent event) {
        return OrderCreatedMessage.builder()
                .trackingId(event.getOrder().getTrackingNumber())
                .customerId(event.getOrder().getCustomerId())
                .orderId(event.getOrder().getId())
                .build();
    }
}

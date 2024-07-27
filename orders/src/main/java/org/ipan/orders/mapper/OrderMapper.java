package org.ipan.orders.mapper;

import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.track.TrackOrderResponse;
import org.ipan.orders.domain.model.Order;

import java.util.ArrayList;
import java.util.UUID;

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

        createOrderCommand.getItems().stream().map(item -> {
            order.addItem(item.getProductId(), item.getQuantity());
        });

    }
}

package org.ipan.orders.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.application.model.track.TrackOrderQuery;
import org.ipan.orders.application.model.track.TrackOrderResponse;
import org.ipan.orders.domain.exception.OrderNotFoundException;
import org.ipan.orders.domain.model.Order;
import org.ipan.orders.domain.repository.OrderRepository;
import org.ipan.orders.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderTrackCommandHandler {
    private final OrderMapper orderDataMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
           Optional<Order> orderResult = orderRepository.findByTrackingId(trackOrderQuery.getTrackingId());
           if (orderResult.isEmpty()) {
               log.warn("Could not find order with tracking id: {}", trackOrderQuery.getTrackingId());
               throw new OrderNotFoundException("Could not find order with tracking id: " + trackOrderQuery.getTrackingId());
           }
           return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}

package org.ipan.orders.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.create.CreateOrderResponse;
import org.ipan.orders.application.model.track.TrackOrderQuery;
import org.ipan.orders.application.model.track.TrackOrderResponse;
import org.ipan.orders.application.ports.input.service.OrderApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        log.info("Creating order for customer: {}", createOrderCommand.getCustomerId());
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id: {}", createOrderResponse.getTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
        TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(TrackOrderQuery.builder().trackingId(trackingId).build());
        log.info("Returning order status with tracking id: {}", trackOrderResponse.getTrackingId());
        return  ResponseEntity.ok(trackOrderResponse);
    }
}

package org.ipan.orders.application.ports.input.service;

import jakarta.validation.Valid;
import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.create.CreateOrderResponse;
import org.ipan.orders.application.model.track.TrackOrderQuery;
import org.ipan.orders.application.model.track.TrackOrderResponse;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
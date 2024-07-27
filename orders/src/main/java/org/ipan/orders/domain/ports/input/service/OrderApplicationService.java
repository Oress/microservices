package org.ipan.orders.domain.ports.input.service;

import jakarta.validation.Valid;
import org.ipan.orders.dto.create.CreateOrderCommand;
import org.ipan.orders.dto.create.CreateOrderResponse;
import org.ipan.orders.dto.track.TrackOrderQuery;
import org.ipan.orders.dto.track.TrackOrderResponse;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}

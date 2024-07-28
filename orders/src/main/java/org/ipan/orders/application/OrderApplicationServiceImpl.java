package org.ipan.orders.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.common.dto.messaging.payment.PaymentCompletedMessage;
import org.ipan.orders.application.handlers.OrderCreateCommandHandler;
import org.ipan.orders.application.handlers.OrderTrackCommandHandler;
import org.ipan.orders.application.handlers.PaymentCompletedCommandHandler;
import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.create.CreateOrderResponse;
import org.ipan.orders.application.model.track.TrackOrderQuery;
import org.ipan.orders.application.model.track.TrackOrderResponse;
import org.ipan.orders.application.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
@AllArgsConstructor
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;
    private final PaymentCompletedCommandHandler paymentCompletedCommandHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }

    @Override
    public void completePayment(PaymentCompletedMessage paymentCompletedMessage) {
        paymentCompletedCommandHandler.completePayment(paymentCompletedMessage);
    }
}

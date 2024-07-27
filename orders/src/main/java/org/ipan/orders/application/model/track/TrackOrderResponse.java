package org.ipan.orders.application.model.track;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.ipan.common.domain.OrderStatus;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class TrackOrderResponse {
    @NotNull
    private String trackingId;
    @NotNull
    private OrderStatus orderStatus;
    private List<String> failureMessages;
}

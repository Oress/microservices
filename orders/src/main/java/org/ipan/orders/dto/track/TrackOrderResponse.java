package org.ipan.orders.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ipan.common.domain.OrderStatus;

import java.util.List;
import java.util.UUID;

@Data
public class TrackOrderResponse {
    @NotNull
    private UUID trackingId;
    @NotNull
    private OrderStatus orderStatus;
    private List<String> failureMessages;
}

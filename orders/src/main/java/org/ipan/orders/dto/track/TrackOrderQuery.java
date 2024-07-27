package org.ipan.orders.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TrackOrderQuery {
    @NotNull
    private UUID trackingId;
}

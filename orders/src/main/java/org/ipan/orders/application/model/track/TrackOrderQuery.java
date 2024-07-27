package org.ipan.orders.application.model.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class TrackOrderQuery {
    @NotNull
    private String trackingId;
}

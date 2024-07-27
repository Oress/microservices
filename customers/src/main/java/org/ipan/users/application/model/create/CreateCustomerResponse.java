package org.ipan.users.application.model.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCustomerResponse {
    @NotNull
    private UUID customerId;
    @NotNull
    private String message;
}

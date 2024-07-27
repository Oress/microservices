package org.ipan.users.application.model.create;

import lombok.*;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateCustomerCommand {
    private UUID customerId;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}

package org.ipan.users.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDTO {
    private UUID id;
    private String firstName;
    private String lastName;
}

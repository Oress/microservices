package org.ipan.users.application.ports.input.service;

import jakarta.validation.Valid;
import org.ipan.users.application.model.create.CreateCustomerCommand;
import org.ipan.users.application.model.create.CreateCustomerResponse;

public interface CustomerApplicationService {
    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);
}

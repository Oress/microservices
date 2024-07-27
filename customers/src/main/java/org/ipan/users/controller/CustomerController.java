package org.ipan.users.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.users.application.ports.input.service.CustomerApplicationService;
import org.ipan.users.application.model.create.CreateCustomerCommand;
import org.ipan.users.application.model.create.CreateCustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private CustomerApplicationService customerApplicationService;

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody CreateCustomerCommand createCustomerCommand) {
        log.info("Creating customer with username: {}", createCustomerCommand.getUsername());
        CreateCustomerResponse response = customerApplicationService.createCustomer(createCustomerCommand);
        return ResponseEntity.ok(response);
    }
}

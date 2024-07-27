package org.ipan.users.application;

import org.ipan.users.application.mapper.CustomerMapper;
import org.ipan.users.application.ports.output.message.publisher.CustomerMessagePublisher;

import lombok.extern.slf4j.Slf4j;
import org.ipan.users.application.ports.input.service.CustomerApplicationService;
import org.ipan.users.application.model.create.CreateCustomerCommand;
import org.ipan.users.application.model.create.CreateCustomerResponse;
import org.ipan.users.domain.event.CustomerCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class CustomerApplicationServiceImpl implements CustomerApplicationService {
    private final CustomerCreateCommandHandler customerCreateCommandHandler;

    private final CustomerMapper customerMapper;

    private final CustomerMessagePublisher customerMessagePublisher;

    public CustomerApplicationServiceImpl(CustomerCreateCommandHandler customerCreateCommandHandler,
                                          CustomerMapper customerMapper,
                                          CustomerMessagePublisher customerMessagePublisher) {
        this.customerCreateCommandHandler = customerCreateCommandHandler;
        this.customerMapper = customerMapper;
        this.customerMessagePublisher = customerMessagePublisher;
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerCommand createCustomerCommand) {
        CustomerCreatedEvent customerCreatedEvent = customerCreateCommandHandler.createCustomer(createCustomerCommand);
        customerMessagePublisher.publish(customerCreatedEvent);
        return customerMapper.customerToCreateCustomerResponse(customerCreatedEvent.getCustomer(), "Customer saved successfully!");
    }
}

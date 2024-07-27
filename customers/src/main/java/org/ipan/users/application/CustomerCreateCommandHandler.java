package org.ipan.users.application;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.users.application.mapper.CustomerMapper;
import org.ipan.users.application.model.create.CreateCustomerCommand;
import org.ipan.users.domain.event.CustomerCreatedEvent;
import org.ipan.users.domain.exception.CustomerDomainException;
import org.ipan.users.domain.model.Customer;
import org.ipan.users.domain.repository.CustomerRepository;
import org.ipan.users.domain.service.CustomerDomainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
class CustomerCreateCommandHandler {
    private final CustomerDomainService customerDomainService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerDataMapper;

    @Transactional
    public CustomerCreatedEvent createCustomer(CreateCustomerCommand createCustomerCommand) {
        Customer customer = customerDataMapper.createCustomerCommandToCustomer(createCustomerCommand);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateCustomer(savedCustomer);
        if (savedCustomer == null) {
            log.error("Could not save customer with id: {}", createCustomerCommand.getCustomerId());
            throw new CustomerDomainException("Could not save customer with id " +
                    createCustomerCommand.getCustomerId());
        }
        log.info("Returning CustomerCreatedEvent for customer id: {}", createCustomerCommand.getCustomerId());
        return customerCreatedEvent;
    }
}

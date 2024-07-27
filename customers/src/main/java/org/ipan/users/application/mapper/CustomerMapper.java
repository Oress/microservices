package org.ipan.users.application.mapper;

import org.ipan.users.application.model.create.CreateCustomerCommand;
import org.ipan.users.application.model.create.CreateCustomerResponse;
import org.ipan.users.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        Customer customer = new Customer();
        customer.setUsername(createCustomerCommand.getUsername());
        customer.setFirstName(createCustomerCommand.getFirstName());
        customer.setLastName(createCustomerCommand.getLastName());
        return customer;
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId(), message);
    }
}

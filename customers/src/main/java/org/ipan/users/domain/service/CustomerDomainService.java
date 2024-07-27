package org.ipan.users.domain.service;


import org.ipan.users.domain.event.CustomerCreatedEvent;
import org.ipan.users.domain.model.Customer;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);
}

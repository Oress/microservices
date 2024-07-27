package org.ipan.users.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.ipan.users.domain.event.CustomerCreatedEvent;
import org.ipan.users.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
public class CustomerDomainServiceImpl implements CustomerDomainService {
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        //Any Business logic required to run for a customer creation
        log.info("Customer with id: {} is initiated", customer.getId());
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}


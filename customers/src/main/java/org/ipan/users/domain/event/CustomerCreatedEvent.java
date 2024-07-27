package org.ipan.users.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ipan.common.domain.event.DomainEvent;
import org.ipan.users.domain.model.Customer;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class CustomerCreatedEvent implements DomainEvent<Customer> {
    private final Customer customer;
    private final ZonedDateTime createdAt;
}

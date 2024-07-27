package org.ipan.users.mapper;

import org.ipan.users.domain.model.Customer;
import org.ipan.users.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        return customerDTO;
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        return customer;
    }
}

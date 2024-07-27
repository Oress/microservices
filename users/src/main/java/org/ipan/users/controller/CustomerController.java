package org.ipan.users.controller;

import lombok.AllArgsConstructor;
import org.ipan.users.domain.repository.CustomerRepository;
import org.ipan.users.dto.CustomerDTO;
import org.ipan.users.mapper.CustomerMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDTO).toList();
    }
}

package org.cesarlead.customerservice.service;

import org.cesarlead.customerservice.dto.CustomerDTO;
import org.cesarlead.customerservice.exception.ResourceNotFoundException;
import org.cesarlead.customerservice.model.Customer;
import org.cesarlead.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public CustomerDTO findCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found: " + id));

        CustomerDTO customerDTO = ToDTO(customer);

        return customerDTO;
    }

    private CustomerDTO ToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );

        return customerDTO;
    }

    private Customer ToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.id(),
                customerDTO.firstName(),
                customerDTO.lastName(),
                customerDTO.email()
        );

        return customer;
    }
}

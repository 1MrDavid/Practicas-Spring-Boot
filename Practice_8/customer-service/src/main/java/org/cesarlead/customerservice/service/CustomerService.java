package org.cesarlead.customerservice.service;


import org.cesarlead.customerservice.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO findCustomerById(Long id);
}

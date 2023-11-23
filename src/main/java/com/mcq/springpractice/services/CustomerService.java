package com.mcq.springpractice.services;

import com.mcq.springpractice.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO createCustomer(CustomerDTO customer);

    void updateCustomerById(UUID id, CustomerDTO customer);

    void deleteCustomerById(UUID id);
}

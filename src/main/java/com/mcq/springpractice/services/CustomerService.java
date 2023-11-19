package com.mcq.springpractice.services;

import com.mcq.springpractice.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);

    Customer createCustomer(Customer customer);

}

package com.mcq.springpractice.mappers;

import com.mcq.springpractice.entities.Customer;
import com.mcq.springpractice.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}

package com.development.customer.application.output.port;

import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.infraestructure.output.repository.entity.Customer;

import java.util.Optional;

public interface CustomerAdapterPort {
    CustomerResponseDto save(Customer customerDto);
    Optional<Customer> findById(Long id);
    void delete(Customer customer);
}

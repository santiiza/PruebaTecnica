package com.development.customer.infraestructure.output.adapter.impl;

import com.development.customer.application.output.port.CustomerAdapterPort;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.infraestructure.input.adapter.rest.mapper.CustomerMapper;
import com.development.customer.infraestructure.output.repository.CustomerRepository;
import com.development.customer.infraestructure.output.repository.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerAdapterPortImpl implements CustomerAdapterPort {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto save(Customer customer) {
        return customerMapper.toResponseDto(
                customerRepository.save(customer));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

}

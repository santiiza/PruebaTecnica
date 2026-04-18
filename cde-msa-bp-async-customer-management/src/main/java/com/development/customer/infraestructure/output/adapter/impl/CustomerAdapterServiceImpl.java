package com.development.customer.infraestructure.output.adapter.impl;

import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.application.output.port.CustomerAdapterService;
import com.development.customer.domain.dto.CustomerPatchRequestDto;
import com.development.customer.infraestructure.exception.CustomNotFoundException;
import com.development.customer.infraestructure.output.adapter.mapper.CustomerMapper;
import com.development.customer.infraestructure.output.messaging.CustomerEventPublisher;
import com.development.customer.infraestructure.output.repository.CustomerRepository;
import com.development.customer.infraestructure.output.repository.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerAdapterServiceImpl implements CustomerAdapterService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerEventPublisher eventPublisher;

    @Override
    public CustomerResponseDto saveCustomer(CustomerRequestDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setClId(generateCustomerId());
        customer.setStatus(true);

        Customer savedCustomer = customerRepository.save(customer);
        eventPublisher.publishCustomerCreated(customerMapper.toCustomerCreatedEvent(savedCustomer));

        return customerMapper.toResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto saveCustomer(Long id, CustomerRequestDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Customer [" + id + "] not found"));

        customer.setName(customerDto.getName());
        customer.setGender(customerDto.getGender());
        customer.setAge(customerDto.getAge());
        customer.setIdentification(customerDto.getIdentification());
        customer.setAddress(customerDto.getAddress());
        customer.setPhone(customerDto.getPhone());
        customer.setPassword(customerDto.getPassword());
        customer.setStatus(customerDto.getStatus());

        Customer savedCustomer = customerRepository.save(customer);
        eventPublisher.publishCustomerUpdated(customerMapper.toCustomerUpdatedEvent(savedCustomer));

        return customerMapper.toResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto saveCustomer(Long id, CustomerPatchRequestDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Customer [" + id + "] not found"));

        if (customerDto.getName() != null) {
            customer.setName(customerDto.getName());
        }
        if (customerDto.getGender() != null) {
            customer.setGender(customerDto.getGender());
        }
        if (customerDto.getAge() != null) {
            customer.setAge(customerDto.getAge());
        }
        if (customerDto.getIdentification() != null) {
            customer.setIdentification(customerDto.getIdentification());
        }
        if (customerDto.getAddress() != null) {
            customer.setAddress(customerDto.getAddress());
        }
        if (customerDto.getPhone() != null) {
            customer.setPhone(customerDto.getPhone());
        }
        if (customerDto.getClId() != null) {
            customer.setClId(customerDto.getClId());
        }
        if (customerDto.getPassword() != null) {
            customer.setPassword(customerDto.getPassword());
        }
        if (customerDto.getStatus() != null) {
            customer.setStatus(customerDto.getStatus());
        }

        Customer savedCustomer = customerRepository.save(customer);
        eventPublisher.publishCustomerUpdated(customerMapper.toCustomerUpdatedEvent(savedCustomer));

        return customerMapper.toResponseDto(savedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        return customerMapper.toResponseDto(customerRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Customer [" + id + "] not found")));
    }

    @Override
    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Customer [" + id + "] not found"));
        customerRepository.delete(customer);
        eventPublisher.publishCustomerDeleted(customerMapper.toCustomerDeletedEvent(customer));
    }

    private String generateCustomerId() {
        return "CLI-" + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }

}

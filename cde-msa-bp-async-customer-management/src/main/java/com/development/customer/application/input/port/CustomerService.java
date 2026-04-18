package com.development.customer.application.input.port;

import com.development.customer.domain.dto.CustomerPatchRequestDto;
import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto request);
    CustomerResponseDto getCustomerById(Long id);
    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto request);
    CustomerResponseDto updateCustomer(Long id, CustomerPatchRequestDto request);
    void deleteCustomer(Long id);
}

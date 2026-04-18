package com.development.customer.application.output.port;

import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.domain.dto.CustomerPatchRequestDto;

public interface CustomerAdapterService {
    CustomerResponseDto saveCustomer(CustomerRequestDto customerDto);
    CustomerResponseDto saveCustomer(Long id, CustomerRequestDto request);
    CustomerResponseDto saveCustomer(Long id, CustomerPatchRequestDto request);
    CustomerResponseDto getCustomerById(Long id);
    void deleteCustomer(Long id);
}

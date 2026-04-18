package com.development.customer.application.service;

import com.development.customer.application.input.port.CustomerService;
import com.development.customer.application.output.port.CustomerAdapterService;
import com.development.customer.domain.dto.CustomerPatchRequestDto;
import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerAdapterService customerAdapterService;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto request) {
        return customerAdapterService.saveCustomer(request);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        return customerAdapterService.getCustomerById(id);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto request) {
        return customerAdapterService.saveCustomer(id, request);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerPatchRequestDto request) {
        return customerAdapterService.saveCustomer(id, request);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerAdapterService.deleteCustomer(id);
    }
}

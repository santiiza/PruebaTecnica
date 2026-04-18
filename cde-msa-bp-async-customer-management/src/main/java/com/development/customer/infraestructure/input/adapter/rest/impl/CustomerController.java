package com.development.customer.infraestructure.input.adapter.rest.impl;

import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.application.input.port.CustomerService;
import com.development.customer.domain.dto.CustomerPatchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestDto request) {
        return customerService.updateCustomer(id, request);
    }

    @PatchMapping("/{id}")
    public CustomerResponseDto updateCustomerPartially(
            @PathVariable Long id,
            @RequestBody CustomerPatchRequestDto request) {
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

}

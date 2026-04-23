package com.development.customer.infraestructure.input.adapter.rest.impl;

import com.development.customer.application.input.port.CustomerInputPort;
import com.development.customer.domain.dto.CustomerPatchRequestDto;
import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerInputPort customerInputPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerRequestDto request) {
        log.info("|-> Started createCustomer");
        return customerInputPort.createCustomer(request);
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomerById(@PathVariable Long id) {
        log.info("|-> Started getCustomerById id: {}", id);
        return customerInputPort.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto request) {
        log.info("|-> Started updateCustomer id: {}", id);
        return customerInputPort.updateCustomer(id, request);
    }

    @PatchMapping("/{id}")
    public CustomerResponseDto updateCustomerPartially(
            @PathVariable Long id,
            @Valid @RequestBody CustomerPatchRequestDto request) {
        log.info("|-> Started updateCustomerPartially id: {}", id);
        return customerInputPort.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        log.info("|-> Started deleteCustomer id: {}", id);
        customerInputPort.deleteCustomer(id);
    }

}

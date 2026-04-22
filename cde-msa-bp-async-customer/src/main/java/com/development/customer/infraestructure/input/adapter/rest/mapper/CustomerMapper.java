package com.development.customer.infraestructure.input.adapter.rest.mapper;

import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.domain.dto.messaging.CustomerDeletedEventDto;
import com.development.customer.domain.dto.messaging.CustomerManagementEventDto;
import com.development.customer.infraestructure.output.repository.entity.Customer;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerRequestDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setGender(dto.getGender());
        customer.setAge(dto.getAge());
        customer.setIdentification(dto.getIdentification());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());
        return customer;
    }

    public CustomerResponseDto toResponseDto(Customer entity) {
        return CustomerResponseDto.builder()
                .id(entity.getId())
                .clientId(entity.getClientId())
                .name(entity.getName())
                .gender(entity.getGender())
                .age(entity.getAge())
                .identification(entity.getIdentification())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .status(entity.getStatus())
                .build();
    }

    public CustomerManagementEventDto toCustomerCreatedEvent(CustomerResponseDto entity) {
        return CustomerManagementEventDto.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(Instant.now())
                .clientId(entity.getClientId())
                .name(entity.getName())
                .status(entity.getStatus())
                .build();
    }

    public CustomerDeletedEventDto toCustomerDeletedEvent(Customer entity) {
        return CustomerDeletedEventDto.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(Instant.now())
                .clientId(entity.getClientId())
                .build();
    }
}

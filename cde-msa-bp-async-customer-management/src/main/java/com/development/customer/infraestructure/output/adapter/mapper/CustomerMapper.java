package com.development.customer.infraestructure.output.adapter.mapper;

import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.domain.dto.messaging.CustomerCreatedEventDto;
import com.development.customer.domain.dto.messaging.CustomerDeletedEventDto;
import com.development.customer.domain.dto.messaging.CustomerUpdatedEventDto;
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
                .clId(entity.getClId())
                .name(entity.getName())
                .gender(entity.getGender())
                .age(entity.getAge())
                .identification(entity.getIdentification())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .status(entity.getStatus())
                .build();
    }

    public CustomerCreatedEventDto toCustomerCreatedEvent(Customer entity) {
        return CustomerCreatedEventDto.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(Instant.now())
                .peId(entity.getId())
                .clId(entity.getClId())
                .identification(entity.getIdentification())
                .status(entity.getStatus())
                .build();
    }

    public CustomerUpdatedEventDto toCustomerUpdatedEvent(Customer entity) {
        return CustomerUpdatedEventDto.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(Instant.now())
                .peId(entity.getId())
                .clId(entity.getClId())
                .status(entity.getStatus())
                .build();
    }

    public CustomerDeletedEventDto toCustomerDeletedEvent(Customer entity) {
        return CustomerDeletedEventDto.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(Instant.now())
                .peId(entity.getId())
                .clId(entity.getClId())
                .build();
    }
}

package com.development.customer.application.service;

import com.development.customer.application.input.port.CustomerInputPort;
import com.development.customer.application.output.port.CustomerAdapterPort;
import com.development.customer.application.output.port.NotificationAdapterPort;
import com.development.customer.domain.dto.CustomerPatchRequestDto;
import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.infraestructure.exception.CustomNotFoundException;
import com.development.customer.infraestructure.input.adapter.rest.mapper.CustomerMapper;
import com.development.customer.infraestructure.output.repository.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerInputPort {

    private final CustomerAdapterPort customerAdapterPort;
    private final NotificationAdapterPort notificationAdapterPort;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto request) {
        //Crear usuario
        Customer customer = customerMapper.toEntity(request);
        customer.setClientId(generateCustomerId());
        customer.setStatus(true);
        CustomerResponseDto customerResponseDto = customerAdapterPort.save(customer);

        //Publicar evento de cliente creado
        log.info("Notification -> Client {} created", customerResponseDto.getClientId());
        notificationAdapterPort.publishCustomerCreated(customerMapper.toCustomerCreatedEvent(customerResponseDto));
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerAdapterPort.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Customer [" + id + "] not found"));
        return customerMapper.toResponseDto(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto request) {
        Customer customer = customerAdapterPort.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Customer [" + id + "] not found"));
        customer.setName(request.getName());
        customer.setGender(request.getGender());
        customer.setAge(request.getAge());
        customer.setIdentification(request.getIdentification());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        customer.setPassword(request.getPassword());
        customer.setStatus(request.getStatus());
        CustomerResponseDto customerResponseDto = customerAdapterPort.save(customer);
        //Publicar evento de cliente actualizado
        log.info("Notification -> Client {} modified", customerResponseDto.getClientId());
        notificationAdapterPort.publishCustomerUpdated(customerMapper.toCustomerCreatedEvent(customerResponseDto));
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerPatchRequestDto request) {
        Customer customer = customerAdapterPort.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Customer [" + id + "] not found"));
        if (request.getName() != null) {
            customer.setName(request.getName());
        }
        if (request.getGender() != null) {
            customer.setGender(request.getGender());
        }
        if (request.getAge() != null) {
            customer.setAge(request.getAge());
        }
        if (request.getIdentification() != null) {
            customer.setIdentification(request.getIdentification());
        }
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
        if (request.getPhone() != null) {
            customer.setPhone(request.getPhone());
        }
        if (request.getPassword() != null) {
            customer.setPassword(request.getPassword());
        }
        if (request.getStatus() != null) {
            customer.setStatus(request.getStatus());
        }
        CustomerResponseDto customerResponseDto = customerAdapterPort.save(customer);
        //Publicar evento de cliente actualizado
        log.info("Notification -> Client {} modified", customerResponseDto.getClientId());
        notificationAdapterPort.publishCustomerUpdated(customerMapper.toCustomerCreatedEvent(customerResponseDto));
        return customerResponseDto;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerAdapterPort.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Customer [" + id + "] not found"));
        customerAdapterPort.delete(customer);

        //Publicar evento de cliente eliminado
        log.info("Notification -> Client {} deleted", id);
        notificationAdapterPort.publishCustomerDeleted(customerMapper.toCustomerDeletedEvent(customer));
    }

    private String generateCustomerId() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}

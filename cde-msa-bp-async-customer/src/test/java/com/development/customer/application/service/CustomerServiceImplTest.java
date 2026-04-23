package com.development.customer.application.service;

import com.development.customer.application.input.port.CustomerInputPort;
import com.development.customer.application.output.port.CustomerAdapterPort;
import com.development.customer.domain.dto.CustomerResponseDto;
import com.development.customer.infraestructure.input.adapter.rest.bean.DataObject;
import com.development.customer.infraestructure.input.adapter.rest.mapper.CustomerMapper;
import com.development.customer.infraestructure.output.messaging.CustomerEventPublisher;
import com.development.customer.infraestructure.output.repository.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    CustomerAdapterPort customerAdapterPort;
    CustomerMapper customerMapper;
    CustomerEventPublisher eventPublisher;

    CustomerInputPort customerService;
    Customer customerEntity;
    DataObject dataObject;

    @BeforeEach
    void setup() {
        customerAdapterPort = mock(CustomerAdapterPort.class);
        customerMapper = mock(CustomerMapper.class);
        eventPublisher = mock(CustomerEventPublisher.class);
        dataObject = new DataObject();
        customerService = new CustomerServiceImpl(
                customerAdapterPort,
                customerMapper,
                eventPublisher
        );

        customerEntity = new Customer();
    }


    @Test
    void createCustomerSucess() {
        // Arrange
        when(customerMapper.toEntity(dataObject.getCustomerRequestDto())).thenReturn(customerEntity);
        when(customerAdapterPort.save(any(Customer.class))).thenReturn(dataObject.getCustomerResponseDto());

        //Act
        CustomerResponseDto result = customerService.createCustomer(dataObject.getCustomerRequestDto());

        //Asert
        assertNotNull(result);
        assertTrue(result.getStatus());

        // Verificar que se guardó el cliente
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerAdapterPort).save(customerCaptor.capture());

        Customer savedCustomer = customerCaptor.getValue();
        assertNotNull(savedCustomer.getClientId());
        assertTrue(savedCustomer.getStatus());

        // Verificar evento publicado
        verify(eventPublisher, times(1))
                .publishCustomerCreated(any());

        verifyNoMoreInteractions(eventPublisher);
    }

}
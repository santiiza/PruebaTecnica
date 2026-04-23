package com.development.customer.infraestructure.input.adapter.rest.impl;


import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.infraestructure.input.adapter.rest.bean.DataObject;
import com.development.customer.infraestructure.output.messaging.CustomerEventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerControllerIT {

    @MockitoBean
    private CustomerEventPublisher customerEventPublisher;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    DataObject dataObject;

    @BeforeEach
    void setup() {
        dataObject = new DataObject();
    }

    @Test
    void createCustomerSuccessfully() throws Exception {
        // Arrange
        CustomerRequestDto request = dataObject.getCustomerRequestDto();

        // Act & Assert
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jose Lema"))
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.clientId").isNotEmpty());
    }

}
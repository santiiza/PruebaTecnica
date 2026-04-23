package com.development.customer.infraestructure.input.adapter.rest.bean;

import com.development.customer.domain.dto.CustomerRequestDto;
import com.development.customer.domain.dto.CustomerResponseDto;

public class DataObject {

    public CustomerRequestDto getCustomerRequestDto() {
        return CustomerRequestDto.builder()
                .name("Jose Lema")
                .gender("M")
                .age(30)
                .identification("1234567890")
                .address("Otavalo sn y principal")
                .phone("098254785")
                .password("1234")
                .build();
    }

    public CustomerResponseDto getCustomerResponseDto() {
        return CustomerResponseDto.builder()
                .name("Jose Lema")
                .status(true)
                .build();
    }
}
package com.development.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    Long id;
    String clientId;
    String name;
    String gender;
    Integer age;
    String identification;
    String address;
    String phone;
    Boolean status;
}

package com.development.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    String name;
    String gender;
    Integer age;
    String identification;
    String address;
    String phone;
    String password;
    Boolean status;
}

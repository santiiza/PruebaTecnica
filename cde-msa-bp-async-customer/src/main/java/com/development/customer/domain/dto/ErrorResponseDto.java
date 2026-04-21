package com.development.customer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
}

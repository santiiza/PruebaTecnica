package com.development.account.domain.dto.Transaction;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Caracteres inválidos")
    @Size(max = 20, message = "debe tener máximo {max} caracteres.")
    String accountNumber;
    @NotNull(message = "El monto no puede ser nulo")
    @Digits(integer = 15, fraction = 2, message = "Máximo 15 dígitos enteros y 2 decimales")
    BigDecimal amount;
}

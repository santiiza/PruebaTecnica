package com.development.account.domain.dto.Account;

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
public class AccountRequestDto {
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "Caracteres inválidos")
    @Size(max = 20, message = "debe tener máximo {max} caracteres.")
    String accountNumber;
    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Solo se permiten letras y espacios")
    @Size(max = 80, message = "debe tener máximo {max} caracteres.")
    String customer;
    @NotBlank(message = "El tipo de cuenta no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Caracteres inválidos")
    @Size(max = 20, message = "debe tener máximo {max} caracteres.")
    String accountType;
    @PositiveOrZero
    @Digits(integer = 15, fraction = 2, message = "Máximo 15 dígitos enteros y 2 decimales")
    BigDecimal initialBalance;
    @NotNull(message = "El estado no puede ser nulo")
    Boolean status;
}

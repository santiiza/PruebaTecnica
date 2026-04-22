package com.development.customer.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPatchRequestDto {
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Solo se permiten letras y espacios")
    @Size(max = 80, message = "debe tener máximo {max} caracteres.")
    String name;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Solo se permiten letras")
    @Size(max = 10, message = "debe tener máximo {max} caracteres.")
    String gender;
    @Min(0)
    @Max(120)
    Integer age;
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "Solo se permiten caracteres alfanuméricos")
    @Size(max = 15, message = "debe tener máximo {max} caracteres.")
    String identification;
    @Pattern(regexp = "^[0-9a-zA-Z .,-ñÑ&]+$", message = "Existen caracteres inválidos")
    @Size(max = 256, message = "debe tener máximo {max} caracteres.")
    String address;
    @Pattern(regexp = "^[0-9]+$", message = "Solo se permiten números")
    @Size(max = 20, message = "debe tener máximo {max} caracteres.")
    String phone;
    @Size(max = 256, message = "debe tener máximo {max} caracteres.")
    String password;
    Boolean status;
}

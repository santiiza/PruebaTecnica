package com.development.customer.infraestructure.output.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "co_cliente", schema = "core")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Person {
    @Column(name = "cl_id", nullable = false, unique = true)
    private String clId;

    @Column(name = "cl_contrasenia", nullable = false)
    private String password;

    @Column(name = "cl_estado", nullable = false)
    private Boolean status;
}

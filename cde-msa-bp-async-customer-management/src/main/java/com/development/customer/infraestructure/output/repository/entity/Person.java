package com.development.customer.infraestructure.output.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "co_persona", schema = "core")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pe_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "pe_nombre", nullable = false)
    private String name;

    @Column(name = "pe_genero", nullable = false)
    private String gender;

    @Column(name = "pe_edad")
    private Integer age;

    @Column(name = "pe_identificacion", nullable = false, unique = true)
    private String identification;

    @Column(name = "pe_direccion")
    private String address;

    @Column(name = "pe_telefono")
    private String phone;

}

package com.development.account.infraestructure.output.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cliente_view", schema = "pasivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientView {

    @Id
    @Column(name = "id", nullable = false, length = 30)
    String clientId;

    @Column(name = "nombre", nullable = false, length = 80)
    String name;

    @Column(name = "estado",nullable = false)
    Boolean status;
}

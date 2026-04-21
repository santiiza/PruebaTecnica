package com.development.account.infraestructure.output.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pa_cuenta", schema = "pasivo")
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "cu_numero", length =20)
    private String accountNumber;

    @Column(name = "cl_id", nullable = false, length = 30)
    private String clId;

    @Column(name = "cu_tipo_cuenta", nullable = false, length = 20)
    private String accountType;

    @Column(name = "cu_saldo_inicial", nullable = false, precision = 15, scale = 2)
    private BigDecimal initialBalance;

    @Column(name = "cu_estado", nullable = false)
    private Boolean status;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    List<Transaction> transactions = new ArrayList<>();
}

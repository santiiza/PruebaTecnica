package com.development.account.infraestructure.output.repository.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pa_movimiento", schema = "pasivo")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mo_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "cu_numero", nullable = false)
    private Account account;

    @Column(name = "mo_fecha", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "mo_tipo_movimiento", nullable = false, length = 20)
    private String transactionType;

    @Column(name = "mo_monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "mo_saldo", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

}

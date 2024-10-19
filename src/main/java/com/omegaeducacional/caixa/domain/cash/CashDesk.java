package com.omegaeducacional.caixa.domain.cash;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "caixa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashDesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String description;
    private Double balance;
}

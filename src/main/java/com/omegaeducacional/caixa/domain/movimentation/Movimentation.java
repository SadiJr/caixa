package com.omegaeducacional.caixa.domain.movimentation;

import com.omegaeducacional.caixa.domain.cash.CashDesk;
import com.omegaeducacional.caixa.domain.enums.TypeMovimentation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity(name = "movimentation")
@Table(name = "movimentation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "typeMovimentation", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TypeMovimentation typeMovimentation;

    @ManyToOne
    private CashDesk cashDesk;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "value", nullable = false, precision = 10)
    private Double value;
}

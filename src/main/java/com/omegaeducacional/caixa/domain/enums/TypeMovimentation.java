package com.omegaeducacional.caixa.domain.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TypeMovimentation {
    E("Entrada"),
    S("Saída");

    String description;

    TypeMovimentation(String description) {
        this.description = description;
    }
}

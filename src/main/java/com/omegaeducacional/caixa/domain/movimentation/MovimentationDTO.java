package com.omegaeducacional.caixa.domain.movimentation;

import com.omegaeducacional.caixa.domain.enums.TypeMovimentation;

import java.util.Date;

public record MovimentationDTO(Long date, TypeMovimentation typeMovimentation, Long deskCash, String description, Double value) {
}

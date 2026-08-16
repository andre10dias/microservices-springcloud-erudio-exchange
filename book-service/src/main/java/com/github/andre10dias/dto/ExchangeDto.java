package com.github.andre10dias.dto;

import java.math.BigDecimal;

public record ExchangeDto(
        Long id,
        String from,
        String to,
        BigDecimal conversionFactor,
        Double conversionValue,
        String enviroment
) {
}

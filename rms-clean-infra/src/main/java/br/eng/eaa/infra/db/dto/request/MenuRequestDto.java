package br.eng.eaa.infra.db.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record MenuRequestDto(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        String imageUrl,
        UUID restaurantId
) {}

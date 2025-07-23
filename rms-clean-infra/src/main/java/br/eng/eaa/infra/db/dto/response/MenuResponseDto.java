package br.eng.eaa.infra.db.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record MenuResponseDto (
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        String imageUrl,
        UUID restaurantId
) {}

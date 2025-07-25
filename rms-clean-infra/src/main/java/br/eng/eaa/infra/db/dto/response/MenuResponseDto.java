package br.eng.eaa.infra.db.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema
public record MenuResponseDto (
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        String imageUrl,
        UUID restaurantId
) {}

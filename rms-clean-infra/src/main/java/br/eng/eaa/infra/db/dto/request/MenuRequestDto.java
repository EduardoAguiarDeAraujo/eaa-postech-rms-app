package br.eng.eaa.infra.db.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Dados de transferência de usuário para a API")
public record MenuRequestDto(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Boolean available,
        String imageUrl,
        UUID restaurantId
) {}

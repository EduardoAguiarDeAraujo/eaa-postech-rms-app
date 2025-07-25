package br.eng.eaa.infra.db.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema
public record RoleResponseDto(
        UUID id,
        String name
) {
}

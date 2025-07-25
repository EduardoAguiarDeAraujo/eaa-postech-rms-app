package br.eng.eaa.infra.db.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema
public record RoleRequestDto(
        UUID id,
        String name
) {}

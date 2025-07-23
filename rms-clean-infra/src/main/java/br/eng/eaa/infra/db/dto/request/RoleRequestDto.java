package br.eng.eaa.infra.db.dto.request;

import java.util.UUID;

public record RoleRequestDto(
        UUID id,
        String name
) {}

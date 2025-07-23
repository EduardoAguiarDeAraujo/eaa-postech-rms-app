package br.eng.eaa.infra.db.dto.response;

import java.util.UUID;

public record RoleResponseDto(
        UUID id,
        String name
) {
}

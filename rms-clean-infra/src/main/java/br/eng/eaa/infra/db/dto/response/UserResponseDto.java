package br.eng.eaa.infra.db.dto.response;

import br.eng.eaa.infra.db.entity.RoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema
public record UserResponseDto (
        UUID id,
        String userName,
        List<RoleEntity> roles
) {}

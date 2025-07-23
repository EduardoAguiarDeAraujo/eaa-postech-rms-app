package br.eng.eaa.infra.db.dto.response;

import br.eng.eaa.infra.db.entity.RoleEntity;

import java.util.List;
import java.util.UUID;

public record UserResponseDto (
        UUID id,
        String userName,
        List<RoleEntity> roles
) {}

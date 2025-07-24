package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.db.dto.request.RoleRequestDto;
import br.eng.eaa.infra.db.dto.response.RoleResponseDto;
import br.eng.eaa.infra.db.entity.RoleEntity;

public class RoleMapper {

    private RoleMapper() { }

    public static Role toRoleDomain(RoleEntity roleEntity) {
        if (roleEntity == null) {
            throw new IllegalArgumentException("RoleEntity must be not null");
        }
        return new Role(roleEntity.getId(), roleEntity.getName());
    }

    public static RoleEntity toRoleEntity(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role must be not null");
        }
        return new RoleEntity(role.getId(), role.getName());
    }

    public static RoleRequest toRequest(RoleRequestDto roleDto) {
        if (roleDto == null) {
            throw new IllegalArgumentException("RoleRequestDto must be not null");
        }
        return new RoleRequest(roleDto.id(), roleDto.name());
    }

    public static RoleResponseDto toDto(RoleResponse roleResponse) {
        if (roleResponse == null) {
            throw new IllegalArgumentException("RoleResponse must be not null");
        }
        return new RoleResponseDto(roleResponse.getId(), roleResponse.getName());
    }
}

package br.eng.eaa.application.model.mapper;

import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;

public class RoleDomainMapper {

    private RoleDomainMapper() {}

    public static Role toRole(RoleRequest roleRequest) {
        if (roleRequest == null) {
            throw new IllegalArgumentException("RoleRequest cannot be null");
        }
        if (roleRequest.getId() == null) {
            return new Role(roleRequest.getName());
        }
        return new Role(roleRequest.getId(), roleRequest.getName());
    }

    public static RoleResponse toResponse(Role role) {
        return new RoleResponse(role.getId(), role.getName());
    }

}

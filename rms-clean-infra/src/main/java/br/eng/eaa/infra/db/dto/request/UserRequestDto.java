package br.eng.eaa.infra.db.dto.request;

import br.eng.eaa.infra.db.entity.RoleEntity;

import java.util.List;
import java.util.UUID;

public record UserRequestDto (
        UUID id,
        String userName,
        String password,
        List<RoleEntity> roles
) {
    public String getData() {
        return String.format("UserRequestDto{id=%s, userName='%s', roles=%s}", id, userName, roles);
    }
}

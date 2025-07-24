package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public class RoleGatewayStub implements IRoleGateway {
    @Override
    public Role save(Role role) {
        return new Role(role.getId(), role.getName());
    }

    @Override
    public Role update(Role role) {
        return new Role(role.getId(), role.getName());
    }

    @Override
    public Role findById(UUID id) {
        return new Role(id, "ADMIN");
    }

    @Override
    public List<Role> findAll() {
        return List.of(new Role(UUID.randomUUID(),"ADMIN"),new Role(UUID.randomUUID(),"OWNER"));
    }

    @Override
    public Boolean delete(UUID id) {
        return true;
    }
}

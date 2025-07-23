package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public class RoleGatewayStub implements IRoleGateway {
    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Role findById(UUID id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }
}

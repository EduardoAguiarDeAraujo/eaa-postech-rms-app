package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public interface IRoleGateway {
    Role save(Role role);
    Role update(Role role);
    Role findById(UUID id);
    List<Role> findAll();
    Boolean delete(UUID id);
}

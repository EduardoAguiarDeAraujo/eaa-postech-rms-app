package br.eng.eaa.application.boundary.output;

import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;

import java.util.List;

public interface RoleOutputPort {
    RoleResponse execute(Role role);
    List<RoleResponse> execute(List<Role> role);
    Boolean execute(Boolean isDeleted);
}

package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.boundary.output.RoleOutputPort;
import br.eng.eaa.application.model.mapper.RoleDomainMapper;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;

import java.util.List;

public class RolePresenter implements RoleOutputPort {

    @Override
    public RoleResponse execute(Role role) {
        return RoleDomainMapper.toResponse(role);
    }

    @Override
    public List<RoleResponse> execute(List<Role> role) {
        return role.stream().map(RoleDomainMapper::toResponse).toList();
    }

    @Override
    public Boolean execute(Boolean isDeleted) {
        return isDeleted;
    }

}

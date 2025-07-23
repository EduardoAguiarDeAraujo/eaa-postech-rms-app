package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IRoleGateway;
import br.eng.eaa.application.boundary.input.RoleInputPort;
import br.eng.eaa.application.boundary.output.RoleOutputPort;
import br.eng.eaa.application.model.mapper.RoleDomainMapper;
import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public class RoleInteractor implements RoleInputPort {

    private IRoleGateway roleGateway;
    private RoleOutputPort roleOutput;

    public RoleInteractor(IRoleGateway roleGateway, RoleOutputPort roleOutput) {
        this.roleGateway = roleGateway;
        this.roleOutput = roleOutput;
    }

    @Override
    public RoleResponse save(RoleRequest roleRequest) {
        Role role = RoleDomainMapper.toRole(roleRequest);
        Role savedRole = roleGateway.save(role);
        return roleOutput.execute(savedRole) ;
    }

    @Override
    public RoleResponse update(RoleRequest roleRequest) {
        Role role = RoleDomainMapper.toRole(roleRequest);
        Role updatedRole = roleGateway.update(role);
        return roleOutput.execute(updatedRole);
    }

    @Override
    public RoleResponse findById(UUID id) {
        Role role = roleGateway.findById(id);
        return roleOutput.execute(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        List<Role> roles = roleGateway.findAll();
        return roleOutput.execute(roles);
    }

    @Override
    public Boolean delete(UUID id) {
        Boolean isDeleted = roleGateway.delete(id);
        return roleOutput.execute(isDeleted);
    }
}

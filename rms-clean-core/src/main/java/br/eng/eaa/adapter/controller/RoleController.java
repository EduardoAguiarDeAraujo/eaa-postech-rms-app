package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IRoleGateway;
import br.eng.eaa.adapter.presenter.RolePresenter;
import br.eng.eaa.application.boundary.input.RoleInputPort;
import br.eng.eaa.application.boundary.output.RoleOutputPort;
import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.application.usecase.RoleInteractor;

import java.util.List;
import java.util.UUID;

public class RoleController {

    private RoleInputPort roleInputPort;

    public RoleController(IRoleGateway roleGateway) {
        RoleOutputPort roleOutputPort = new RolePresenter();
        this.roleInputPort = new RoleInteractor(roleGateway, roleOutputPort);
    }

    public RoleResponse save(RoleRequest roleRequest) {
        return roleInputPort.save(roleRequest);
    }

    public RoleResponse update(RoleRequest roleRequest) {
        return roleInputPort.update(roleRequest);
    }

    public Boolean delete(UUID id) {
        return roleInputPort.delete(id);
    }

    public RoleResponse findById(UUID id) {
        return roleInputPort.findById(id);
    }

    public List<RoleResponse> findAll() {
        return roleInputPort.findAll();
    }
}

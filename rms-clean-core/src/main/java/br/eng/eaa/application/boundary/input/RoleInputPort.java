package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;

import java.util.List;
import java.util.UUID;

public interface RoleInputPort {
    RoleResponse save(RoleRequest roleRequest);
    RoleResponse update(RoleRequest roleRequest);
    RoleResponse findById(UUID id);
    List<RoleResponse> findAll();
    Boolean delete(UUID id);
}

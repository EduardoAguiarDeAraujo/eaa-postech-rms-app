package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;

import java.util.List;
import java.util.UUID;

public interface MenuInputPort {
    MenuResponse save(MenuRequest menuRequest);
    MenuResponse update(MenuRequest menuRequest);
    MenuResponse findById(UUID id);
    List<MenuResponse> findAll();
    Boolean delete(UUID id);
}

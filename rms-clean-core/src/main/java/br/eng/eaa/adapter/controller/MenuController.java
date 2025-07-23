package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IMenuGateway;
import br.eng.eaa.adapter.presenter.MenuPresenter;
import br.eng.eaa.application.boundary.input.MenuInputPort;
import br.eng.eaa.application.boundary.output.MenuOutputPort;
import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.application.usecase.MenuInteractor;

import java.util.List;
import java.util.UUID;

public class MenuController {

    private MenuInputPort menuInputPort;

    public MenuController(IMenuGateway menuGateway) {
        MenuOutputPort menuOutputPort = new MenuPresenter();
        this.menuInputPort = new MenuInteractor(menuGateway, menuOutputPort);
    }

    public MenuResponse save(MenuRequest menuRequest) {
        return menuInputPort.save(menuRequest);
    }

    public MenuResponse update(MenuRequest menuRequest) {
        return menuInputPort.update(menuRequest);
    }

    public Boolean delete(UUID id) {
        return menuInputPort.delete(id);
    }

    public MenuResponse findById(UUID id) {
        return menuInputPort.findById(id);
    }

    public List<MenuResponse> findAll(){
        return menuInputPort.findAll();
    }

}

package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IMenuGateway;
import br.eng.eaa.application.boundary.input.MenuInputPort;
import br.eng.eaa.application.boundary.output.MenuOutputPort;
import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Menu;

import java.util.List;
import java.util.UUID;

public class MenuInteractor implements MenuInputPort {

    private IMenuGateway menuGateway;
    private MenuOutputPort menuOutput;

    public MenuInteractor(IMenuGateway menuGateway, MenuOutputPort menuOutput) {
        this.menuGateway = menuGateway;
        this.menuOutput = menuOutput;
    }

    @Override
    public MenuResponse save(MenuRequest menuRequest) {
        Menu menu = toMenu(menuRequest);
        Menu savedMenu = menuGateway.save(menu);
        return menuOutput.execute(savedMenu);
    }

    @Override
    public MenuResponse update(MenuRequest menuRequest) {
        Menu menu = toMenu(menuRequest);
        Menu menuSaved = menuGateway.update(menu);
        return menuOutput.execute(menuSaved);
    }

    @Override
    public MenuResponse findById(UUID id) {
        Menu menu = menuGateway.findById(id);
        return menuOutput.execute(menu);
    }

    @Override
    public List<MenuResponse> findAll() {
        List<Menu> menus = menuGateway.findAll();
        return menuOutput.execute(menus);
    }

    @Override
    public Boolean delete(UUID id) {
        Boolean isDeleted = menuGateway.delete(id);
        return menuOutput.execute(isDeleted);
    }

    private Menu toMenu(MenuRequest menuRequest){
        if (menuRequest.getId() != null){
            return new Menu(menuRequest.getId(), menuRequest.getName(), menuRequest.getDescription(), menuRequest.getPrice(), menuRequest.getAvailable(), menuRequest.getImageUrl(), menuRequest.getRestaurantId());
        }
        return new Menu(menuRequest.getName(), menuRequest.getDescription(), menuRequest.getPrice(), menuRequest.getAvailable(), menuRequest.getImageUrl(), menuRequest.getRestaurantId());
    }
}

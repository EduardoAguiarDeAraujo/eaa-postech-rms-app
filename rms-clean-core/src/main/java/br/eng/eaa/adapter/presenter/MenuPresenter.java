package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.boundary.output.MenuOutputPort;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Menu;

import java.util.List;

public class MenuPresenter implements MenuOutputPort {

    @Override
    public MenuResponse execute(Menu menu) {
        return toResponse(menu);
    }

    @Override
    public List<MenuResponse> execute(List<Menu> menus) {
        return menus.stream().map(MenuPresenter::toResponse).toList();
    }

    @Override
    public Boolean execute(Boolean isDeleted) {
        return isDeleted;
    }

    private static MenuResponse toResponse(Menu menu){
        return new MenuResponse(menu.getId(), menu.getName(), menu.getDescription(), menu.getPrice(), menu.getAvailable(), menu.getImageUrl(), menu.getRestaurantId());
    }

}

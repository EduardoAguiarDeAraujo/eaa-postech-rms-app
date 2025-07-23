package br.eng.eaa.application.boundary.output;

import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Menu;

import java.util.List;

public interface MenuOutputPort {
    MenuResponse execute(Menu menu);
    List<MenuResponse> execute(List<Menu> menu);
    Boolean execute(Boolean isDeleted);
}

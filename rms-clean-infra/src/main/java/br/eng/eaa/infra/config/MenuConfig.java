package br.eng.eaa.infra.config;

import br.eng.eaa.adapter.controller.MenuController;
import br.eng.eaa.infra.db.gateway.MenuGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuConfig {

    private final MenuGateway menuGateway;

    public MenuConfig(MenuGateway menuGateway) {
        this.menuGateway = menuGateway;
    }

    @Bean
    public MenuController menuController() {
        return new MenuController(menuGateway);
    }


}

package br.eng.eaa.infra.config;

import br.eng.eaa.adapter.controller.UserController;
import br.eng.eaa.infra.db.gateway.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final UserGateway userGateway;

    public UserConfig(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Bean
    UserController userController() {
        return new UserController(userGateway);
    }
}

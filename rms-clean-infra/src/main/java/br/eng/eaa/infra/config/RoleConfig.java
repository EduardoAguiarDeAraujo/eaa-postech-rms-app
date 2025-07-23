package br.eng.eaa.infra.config;

import br.eng.eaa.adapter.controller.RoleController;
import br.eng.eaa.infra.db.gateway.RoleGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    private final RoleGateway roleGateway;

    public RoleConfig(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    @Bean
    public RoleController roleController() {
        return new RoleController(roleGateway);
    }
}

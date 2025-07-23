package br.eng.eaa.infra.config;

import br.eng.eaa.adapter.controller.RestaurantController;
import br.eng.eaa.infra.db.gateway.RestaurantGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantConfig {

    private final RestaurantGateway restaurantGateway;

    public RestaurantConfig(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Bean
    public RestaurantController restaurantController() {
        return new RestaurantController(restaurantGateway);
    }
}

package br.eng.eaa.application.boundary.output;

import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Restaurant;

import java.util.List;

public interface RestaurantOutputPort {
    RestaurantResponse execute(Restaurant restaurant);
    List<RestaurantResponse> execute(List<Restaurant> restaurant);
    Boolean execute(Boolean isDeleted);
}

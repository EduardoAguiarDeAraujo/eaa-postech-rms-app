package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;

import java.util.List;
import java.util.UUID;

public interface RestaurantInputPort {
    RestaurantResponse save(RestaurantRequest restaurantRequest);
    RestaurantResponse update(RestaurantRequest restaurantRequest);
    RestaurantResponse findById(UUID id);
    List<RestaurantResponse> findAll();
    Boolean delete(UUID id);
}

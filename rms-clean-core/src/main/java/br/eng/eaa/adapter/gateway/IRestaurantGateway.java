package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Restaurant;

import java.util.List;
import java.util.UUID;

public interface IRestaurantGateway {
    Restaurant save(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);
    Restaurant findById(UUID id);
    List<Restaurant> findAll();
    Boolean delete(UUID id);
}

package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.boundary.output.RestaurantOutputPort;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Restaurant;

import java.util.List;

public class RestaurantPresenter implements RestaurantOutputPort {

    @Override
    public RestaurantResponse execute(Restaurant restaurant) {
        return toResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> execute(List<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantPresenter::toResponse).toList();
    }

    @Override
    public Boolean execute(Boolean isDeleted) {
        return isDeleted;
    }

    private static RestaurantResponse toResponse(Restaurant restaurant){
        return new RestaurantResponse(restaurant.getId(), restaurant.getName(), restaurant.getCuisineType(), restaurant.getOpenTime(), restaurant.getCloseTime(), restaurant.getOwner(), restaurant.getAddress(), restaurant.getMenus());
    }
}

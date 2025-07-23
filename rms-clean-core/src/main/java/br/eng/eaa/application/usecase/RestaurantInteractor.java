package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IRestaurantGateway;
import br.eng.eaa.application.boundary.input.RestaurantInputPort;
import br.eng.eaa.application.boundary.output.RestaurantOutputPort;
import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Restaurant;

import java.util.List;
import java.util.UUID;

public class RestaurantInteractor implements RestaurantInputPort {

    private IRestaurantGateway restaurantGateway;
    private RestaurantOutputPort restaurantOutput;

    public RestaurantInteractor(IRestaurantGateway restaurantGateway, RestaurantOutputPort restaurantOutput) {
        this.restaurantGateway = restaurantGateway;
        this.restaurantOutput = restaurantOutput;
    }

    @Override
    public RestaurantResponse save(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = toRestaurant(restaurantRequest);
        Restaurant restaurantSaved = restaurantGateway.save(restaurant);
        return restaurantOutput.execute(restaurantSaved);
    }

    @Override
    public RestaurantResponse update(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = toRestaurant(restaurantRequest);
        Restaurant restaurantSaved = restaurantGateway.update(restaurant);
        return restaurantOutput.execute(restaurantSaved);
    }

    @Override
    public RestaurantResponse findById(UUID id) {
        Restaurant restaurant = restaurantGateway.findById(id);
        return restaurantOutput.execute(restaurant);
    }

    @Override
    public List<RestaurantResponse> findAll() {
        List<Restaurant> restaurants = restaurantGateway.findAll();
        return restaurantOutput.execute(restaurants);
    }

    @Override
    public Boolean delete(UUID id) {
        Boolean isDeleted = restaurantGateway.delete(id);
        return isDeleted;
    }

    private Restaurant toRestaurant(RestaurantRequest restaurant){
        if (restaurant.getId() == null){
            return new Restaurant(restaurant.getName(), restaurant.getCuisineType(), restaurant.getOpenTime(), restaurant.getCloseTime(), restaurant.getOwner(), restaurant.getAddress(), restaurant.getMenus());
        }
        return new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getCuisineType(), restaurant.getOpenTime(), restaurant.getCloseTime(), restaurant.getOwner(), restaurant.getAddress(), restaurant.getMenus());
    }
}

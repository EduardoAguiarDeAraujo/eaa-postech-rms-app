package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IRestaurantGateway;
import br.eng.eaa.adapter.presenter.RestaurantPresenter;
import br.eng.eaa.application.boundary.input.RestaurantInputPort;
import br.eng.eaa.application.boundary.output.RestaurantOutputPort;
import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.application.usecase.RestaurantInteractor;

import java.util.List;
import java.util.UUID;

public class RestaurantController {

    RestaurantInputPort restaurantInputPort;

    public RestaurantController(IRestaurantGateway restaurantGateway) {
        RestaurantOutputPort restaurantOutputPort = new RestaurantPresenter();
        this.restaurantInputPort = new RestaurantInteractor(restaurantGateway, restaurantOutputPort);
    }

    public RestaurantResponse save(RestaurantRequest restaurantRequest) {
        return restaurantInputPort.save(restaurantRequest);
    }

    public RestaurantResponse update(RestaurantRequest restaurantRequest) {
        return restaurantInputPort.update(restaurantRequest);
    }

    public RestaurantResponse findById(UUID id) {
        return restaurantInputPort.findById(id);
    }

    public List<RestaurantResponse> findAll(){
        return restaurantInputPort.findAll();
    }

    public Boolean delete(UUID id) {
        return restaurantInputPort.delete(id);
    }
}

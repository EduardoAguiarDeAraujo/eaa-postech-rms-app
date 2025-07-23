package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.RestaurantController;
import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.infra.db.dto.request.RestaurantRequestDto;
import br.eng.eaa.infra.db.dto.response.RestaurantResponseDto;
import br.eng.eaa.infra.db.mapper.RestaurantMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {


    private final RestaurantController restaurantController;

    public RestaurantService(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    public RestaurantResponseDto save(RestaurantRequestDto requestDto) {
        RestaurantRequest restaurantRequest = RestaurantMapper.toRequest(requestDto);
        RestaurantResponse savedRestaurant = restaurantController.save(restaurantRequest);
        return RestaurantMapper.toDto(savedRestaurant);
    }

    public RestaurantResponseDto findById(UUID id) {
        RestaurantResponse restaurantResponse = restaurantController.findById(id);
        return RestaurantMapper.toDto(restaurantResponse);
    }

    public RestaurantResponseDto update(RestaurantRequestDto requestDto) {
        RestaurantRequest restaurantRequest = RestaurantMapper.toRequest(requestDto);
        RestaurantResponse updatedRestaurant = restaurantController.update(restaurantRequest);
        return RestaurantMapper.toDto(updatedRestaurant);
    }

    public Boolean delete(UUID id) {
        return restaurantController.delete(id);
    }

    public List<RestaurantResponseDto> findAll() {
        List<RestaurantResponse> restaurants = restaurantController.findAll();
        return restaurants.stream().map(RestaurantMapper::toDto).toList();
    }

}

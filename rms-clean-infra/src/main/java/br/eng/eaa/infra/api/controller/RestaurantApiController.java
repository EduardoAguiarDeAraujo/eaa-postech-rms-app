package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.db.dto.request.RestaurantRequestDto;
import br.eng.eaa.infra.db.dto.response.RestaurantResponseDto;
import br.eng.eaa.infra.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantApiController {

    private final RestaurantService restaurantService;

    public RestaurantApiController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantResponseDto> save(@RequestBody RestaurantRequestDto restaurant){
        RestaurantResponseDto savedRestaurant = restaurantService.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> findById(@PathVariable UUID id) {
        RestaurantResponseDto restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/update")
    public ResponseEntity<RestaurantResponseDto> update(@RequestBody RestaurantRequestDto restaurant) {
        RestaurantResponseDto updatedRestaurant = restaurantService.update(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> findAll() {
        List<RestaurantResponseDto> restaurants = restaurantService.findAll();
        return ResponseEntity.ok(restaurants);
    }
}

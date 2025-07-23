package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.db.dto.request.RestaurantRequestDto;
import br.eng.eaa.infra.db.dto.response.RestaurantResponseDto;
import br.eng.eaa.infra.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantApiController {

    Logger logger = LoggerFactory.getLogger(RestaurantApiController.class);

    private final RestaurantService restaurantService;

    public RestaurantApiController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantResponseDto> save(@RequestBody RestaurantRequestDto restaurant){
        logger.info("Saving restaurant: {} - {}", restaurant, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        RestaurantResponseDto savedRestaurant = restaurantService.save(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> findById(@PathVariable UUID id) {
        logger.info("Finding restaurant by ID: {} - {}", id, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        RestaurantResponseDto restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/update")
    public ResponseEntity<RestaurantResponseDto> update(@RequestBody RestaurantRequestDto restaurant) {
        logger.info("Updating restaurant: {} - {}", restaurant, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        RestaurantResponseDto updatedRestaurant = restaurantService.update(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Deleting restaurant with ID: {} - {}", id, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Boolean isDeleted = restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> findAll() {
        logger.info("Finding all restaurants - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        List<RestaurantResponseDto> restaurants = restaurantService.findAll();
        return ResponseEntity.ok(restaurants);
    }
}

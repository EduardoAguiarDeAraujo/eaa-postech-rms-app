package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.adapter.gateway.IRestaurantGateway;
import br.eng.eaa.domain.entity.Restaurant;
import br.eng.eaa.infra.api.exception.RestaurantNotFoundException;
import br.eng.eaa.infra.db.entity.CuisineTypeEnum;
import br.eng.eaa.infra.db.entity.RestaurantEntity;
import br.eng.eaa.infra.db.entity.UserEntity;
import br.eng.eaa.infra.db.mapper.AddressMapper;
import br.eng.eaa.infra.db.mapper.RestaurantMapper;
import br.eng.eaa.infra.db.repository.RestaurantRepository;
import br.eng.eaa.infra.db.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RestaurantGateway implements IRestaurantGateway {

    public static final String RESTAURANT_NOT_FOUND_WITH_ID = "Restaurant not found with id: ";
    public static final String RESTAURANT_OWNER_NOT_FOUND_WITH_ID = "Restaurant owner not found with id: ";
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantGateway(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = RestaurantMapper.toEntity(restaurant);
        UserEntity userEntity = userRepository.findById(restaurant.getOwner().getId()).orElseThrow(() -> new RestaurantNotFoundException(RESTAURANT_OWNER_NOT_FOUND_WITH_ID + restaurant.getOwner().getId()));
        restaurantEntity.setOwner(userEntity);
        RestaurantEntity savedRestaurantEntity = restaurantRepository.save(restaurantEntity);
        return RestaurantMapper.toDomain(savedRestaurantEntity);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurant.getId()).orElseThrow(() -> new RestaurantNotFoundException(RESTAURANT_NOT_FOUND_WITH_ID + restaurant.getId()));
        UserEntity userEntity = userRepository.findById(restaurant.getOwner().getId()).orElseThrow(() -> new RestaurantNotFoundException(RESTAURANT_OWNER_NOT_FOUND_WITH_ID + restaurant.getOwner().getId()));
        restaurantEntity.setName(restaurant.getName());
        restaurantEntity.setCuisineType(CuisineTypeEnum.valueOf(restaurant.getCuisineType().toString()));
        restaurantEntity.setOwner(userEntity);
        restaurantEntity.setAddress(AddressMapper.toEntity(restaurant.getAddress()));
        RestaurantEntity savedRestaurantEntity = restaurantRepository.save(restaurantEntity);
        return RestaurantMapper.toDomain(savedRestaurantEntity);
    }

    @Override
    public Restaurant findById(UUID id)  {
        return restaurantRepository.findById(id).map(RestaurantMapper::toDomain).orElseThrow(() -> new RestaurantNotFoundException(RESTAURANT_NOT_FOUND_WITH_ID + id));
    }

    @Override
    public List<Restaurant> findAll() {
        List<RestaurantEntity> restaurantsEntities = restaurantRepository.findAll();
        return restaurantsEntities.stream().map(RestaurantMapper::toDomain).toList();
    }

    @Override
    public Boolean delete(UUID id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(RESTAURANT_NOT_FOUND_WITH_ID + id));
        restaurantRepository.delete(restaurantEntity);
        return true;
    }
}

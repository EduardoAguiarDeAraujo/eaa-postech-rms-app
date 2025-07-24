package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Restaurant;
import br.eng.eaa.domain.valueobject.CuisineType;
import br.eng.eaa.infra.db.dto.request.RestaurantRequestDto;
import br.eng.eaa.infra.db.dto.response.RestaurantResponseDto;
import br.eng.eaa.infra.db.entity.CuisineTypeEnum;
import br.eng.eaa.infra.db.entity.RestaurantEntity;

public class RestaurantMapper {

    private RestaurantMapper() {}

    public static RestaurantEntity toEntity(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant must be not null");
        }
        if (restaurant.getCuisineType() == null) {
            return new RestaurantEntity(restaurant.getId(),restaurant.getName());
        }
        return new RestaurantEntity(
                restaurant.getId(),
                restaurant.getName(),
                CuisineTypeEnum.valueOf(restaurant.getCuisineType().toString()),
                restaurant.getOpenTime(),
                restaurant.getCloseTime(),
                UserMapper.toEntity(restaurant.getOwner()),
                AddressMapper.toEntity(restaurant.getAddress()),
                restaurant.getMenus().stream().map(MenuMapper::toEntity).toList()
        );
    }

    public static Restaurant toDomain(RestaurantEntity restaurantEntity) {
        if (restaurantEntity == null) {
            throw new IllegalArgumentException("RestaurantEntity must be not null");
        }
        if (restaurantEntity.getCuisineType() == null) {
            return new Restaurant(
                    restaurantEntity.getId(),
                    restaurantEntity.getName());
        }
        return new Restaurant(
            restaurantEntity.getId(),
            restaurantEntity.getName(),
            CuisineType.valueOf(restaurantEntity.getCuisineType().toString()),
            restaurantEntity.getOpenTime(),
            restaurantEntity.getCloseTime(),
            UserMapper.toDomain(restaurantEntity.getOwner()),
            AddressMapper.toDomain(restaurantEntity.getAddress()),
            restaurantEntity.getMenus().stream().map(MenuMapper::toDomain).toList()
        );
    }

    public static RestaurantRequest toRequest(RestaurantRequestDto restaurantRequestDto) {
        if (restaurantRequestDto == null) {
            throw new IllegalArgumentException("RestaurantRequestDto must be not null");
        }
        return new RestaurantRequest(
            restaurantRequestDto.id(),
            restaurantRequestDto.name(),
            CuisineType.valueOf(restaurantRequestDto.cuisineType().toString()),
            restaurantRequestDto.openTime(),
            restaurantRequestDto.closeTime(),
            UserMapper.toDomain(restaurantRequestDto.owner()),
            AddressMapper.toDomain(restaurantRequestDto.address()),
            restaurantRequestDto.menus().stream().map(MenuMapper::toDomain).toList()
        );
    }

    public static RestaurantResponseDto toDto(RestaurantResponse restaurantResponse) {
        if (restaurantResponse == null) {
            throw new IllegalArgumentException("RestaurantRequest must be not null");
        }
        return new RestaurantResponseDto(
            restaurantResponse.getId(),
            restaurantResponse.getName(),
            CuisineTypeEnum.valueOf(restaurantResponse.getCuisineType().toString()),
            restaurantResponse.getOpenTime(),
            restaurantResponse.getCloseTime(),
            UserMapper.toDto(restaurantResponse.getOwner()),
            AddressMapper.toEntity(restaurantResponse.getAddress()),
            restaurantResponse.getMenus().stream().map(MenuMapper::fromDomainToDto).toList()
        );
    }

}

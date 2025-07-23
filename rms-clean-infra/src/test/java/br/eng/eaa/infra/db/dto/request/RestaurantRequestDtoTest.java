package br.eng.eaa.infra.db.dto.request;

import br.eng.eaa.infra.db.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantRequestDtoTest {

    private UUID id;
    private String name;
    private CuisineTypeEnum cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private UserEntity owner;
    private AddressEntity address;
    private List<MenuEntity> menus;

    @BeforeEach
    void setUp() {
        // Initialize variables before each test
        id = UUID.randomUUID();
        name = "Test Restaurant";
        cuisineType = CuisineTypeEnum.ITALIAN;
        openTime = LocalTime.parse("11:00");
        closeTime = LocalTime.parse("22:00");

        owner = new UserEntity(UUID.randomUUID(), "Eduardo", "MinhaSenhaForte10#", List.of(new RoleEntity(UUID.randomUUID(), "ADMIN"), new RoleEntity(UUID.randomUUID(), "OWNER")));
        address = new AddressEntity("123 Main St", "City", "State", "12345");
    }

    @Test
    @DisplayName("Test RestaurantRequestDto creation")
    void testRestaurantRequestDtoCreation() {
        // Given
        menus = List.of(new MenuEntity(UUID.randomUUID(), "Contra-filé", "Description 1", BigDecimal.valueOf(29.99), true, "http://example.com/image.jpg", new RestaurantEntity(UUID.randomUUID(),"Restauranda da Maria")));

        // When
        var restaurantRequestDto = new RestaurantRequestDto(id, name, cuisineType, openTime, closeTime, owner, address, menus);

        // Then
        assertEquals(id, restaurantRequestDto.id());
        assertEquals(name, restaurantRequestDto.name());
        assertEquals(cuisineType, restaurantRequestDto.cuisineType());
        assertEquals(owner.getId(), restaurantRequestDto.owner().getId());
        assertEquals(address.getStreet(), restaurantRequestDto.address().getStreet());
        assertEquals(menus.size(), restaurantRequestDto.menus().size());
    }
    @Test
    @DisplayName("Test RestaurantRequestDto creation with  menus null")
    void testRestaurantRequestDtoCreationWithMenusNull() {
        // Given
        menus = null;

        // When
        var restaurantRequestDto = new RestaurantRequestDto(id, name, cuisineType, openTime, closeTime, owner, address, menus);

        // Then
        assertEquals(id, restaurantRequestDto.id());
        assertEquals(name, restaurantRequestDto.name());
        assertEquals(cuisineType, restaurantRequestDto.cuisineType());
        assertEquals(owner.getId(), restaurantRequestDto.owner().getId());
        assertEquals(address.getStreet(), restaurantRequestDto.address().getStreet());
        assertEquals(0, restaurantRequestDto.menus().size());
    }



}
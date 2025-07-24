package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantEntityTest {

    private UUID id;
    private String restaurantName;
    private CuisineTypeEnum cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private UserEntity owner;
    private AddressEntity address;
    private List<MenuEntity> menus;

    private String userName;
    private String password;
    private List<RoleEntity> roles;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    private String menuName;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;

    private RestaurantEntity restaurant;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurantName = "Restaurante da Maria";
        cuisineType = CuisineTypeEnum.ITALIAN;
        openTime = LocalTime.of(11, 0);
        closeTime = LocalTime.of(22, 0);
        userName = "Eduardo";
        password = "MinhaSenhaForte10#";
        roles = List.of(
                new RoleEntity(UUID.randomUUID(), "ADMIN"),
                new RoleEntity(UUID.randomUUID(), "OWNER")
        );
        owner = new UserEntity(UUID.randomUUID(), userName, password, roles);
        street = "Av. Paulista, 1106";
        city = "São Paulo";
        state = "SP";
        zipCode = "01311-000";
        address = new AddressEntity(street, city, state, zipCode);

        menuName = "Pizza Margherita";
        description = "Delicious pizza with fresh tomatoes and basil";
        price = BigDecimal.valueOf(29.99);
        available = true;
        imageUrl = "http://example.com/pizza.jpg";
        restaurant = new RestaurantEntity(id, restaurantName);
        menus = List.of(
                new MenuEntity(UUID.randomUUID(), menuName, description, price, available, imageUrl, restaurant),
                new MenuEntity(UUID.randomUUID(), menuName, description, price, available, imageUrl, restaurant)
        );
    }

    @Test
    @DisplayName("Deve retornar um restaurante válido")
    void shouldReturnValidRestaurantEntity() {
        RestaurantEntity validRestaurant = new RestaurantEntity(id, restaurantName, cuisineType, openTime, closeTime, owner, address, menus);

        assertNotNull(validRestaurant);
        assertEquals(id, validRestaurant.getId());
        assertEquals(restaurantName, validRestaurant.getName());
        assertEquals(cuisineType, validRestaurant.getCuisineType());
        assertEquals(owner, validRestaurant.getOwner());
        assertEquals(address, validRestaurant.getAddress());
        assertNotNull(validRestaurant.getMenus());

        System.out.printf("Restaurante válido - Id: %s - %s %n", restaurant.getId(), restaurant.getName());
    }

    @Test
    @DisplayName("Deve criar um restaurante pelo construtor vazio")
    void shouldCreateRestaurantEntityWithEmptyConstructor() {
        RestaurantEntity validRestaurant = new RestaurantEntity();
        validRestaurant.setId(id);
        validRestaurant.setName(restaurantName);
        validRestaurant.setCuisineType(cuisineType);
        validRestaurant.setOwner(owner);
        validRestaurant.setAddress(address);
        validRestaurant.setMenus(menus);

        assertNotNull(validRestaurant);
        assertEquals(id, validRestaurant.getId());
        assertEquals(restaurantName, validRestaurant.getName());
        assertEquals(cuisineType, validRestaurant.getCuisineType());
        assertEquals(owner, validRestaurant.getOwner());
        assertEquals(address, validRestaurant.getAddress());
        assertEquals(menus, validRestaurant.getMenus());

        System.out.printf("Restaurante criado pelo construtor vazio - Id: %s - %s %n", restaurant.getId(), restaurant.getName());
    }

    @Test
    @DisplayName("Deve retornar uma exceção criar um restaurante com id null")
    void shouldThrowExceptionWhenCreatingRestaurantWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> new RestaurantEntity(null, restaurantName, cuisineType, openTime, closeTime, owner, address, menus));
        System.out.printf("Restaurante inválido - Id null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exceção criar um restaurante com nome null")
    void shouldThrowExceptionWhenCreatingRestaurantWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new RestaurantEntity(id, null, cuisineType, openTime, closeTime, owner, address, menus));
        System.out.printf("Restaurante inválido - Nome null %n");
    }

    @Test
    @DisplayName("Deve retornar uma exceção criar um restaurante com nome vazio")
    void shouldThrowExceptionWhenCreatingRestaurantWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new RestaurantEntity(id, "", cuisineType, openTime, closeTime, owner, address, menus));
        System.out.printf("Restaurante inválido - Nome vazio %n");
    }

    @Test
    @DisplayName("Deve retornar uma exceção criar um restaurante com nome em branco")
    void shouldThrowExceptionWhenCreatingRestaurantWithBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new RestaurantEntity(id, "  ", cuisineType, openTime, closeTime, owner, address, menus));
        System.out.printf("Restaurante inválido - Nome em branco %n");
    }

}
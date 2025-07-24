package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantPresenterTest {

    private UUID id;
    private String name;
    private CuisineType cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private User owner;
    private Address address;
    private List<Menu> menus;
    private UUID restaurantId;

    private Restaurant restaurant;
    private RestaurantResponse restaurantResponse;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.name = "Restaurante da Maria";
        this.restaurantId = UUID.randomUUID();
        this.openTime = LocalTime.of(11, 0);
        this.closeTime = LocalTime.of(22, 0);
        List<Role> roles = List.of(new Role("ADMIN"), new Role("OWNER"));
        this.menus = List.of(
                new Menu("Contra-filé ", "Contra-file com fritas, arroz branco e feijão", BigDecimal.valueOf(45.00), true, "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg", restaurantId),
                new Menu("Contra-filé a cavalo", "Contra-filé com ovo frito, arroz brando e feijão carioquinha",BigDecimal.valueOf(47.00), true, "https://sabores-new.s3.amazonaws.com/public/2024/11/bife-a-cavalo-1024x494.jpg", restaurantId)
        );
        this.address = new Address("Av. Paulista, 1106", "São Paulo", "SP", "01311-000");
        this.cuisineType = CuisineType.BRASILIAN;
        this.owner = new User (UUID.randomUUID(), "Eduardo",  roles);

        this.restaurantResponse = new RestaurantResponse(id, name, cuisineType, openTime, closeTime, owner, address, menus);
        this.restaurant = new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus);
    }

    @Test
    @DisplayName("Deve retornar um restaurant válido")
    void shouldReturnValidRestaurant(){
        //WHEN
        RestaurantResponse actualResponse = new RestaurantPresenter().execute(restaurant);

        //THEN
        assertEquals(restaurantResponse.getId(), actualResponse.getId());
        assertEquals(restaurantResponse.getName(), actualResponse.getName());
        assertEquals(restaurantResponse.getCuisineType(), actualResponse.getCuisineType());
        assertEquals(restaurantResponse.getOwner(), actualResponse.getOwner());
        assertEquals(restaurantResponse.getAddress(), actualResponse.getAddress());
        assertEquals(restaurantResponse.getMenus().size(), actualResponse.getMenus().size());
    }

    @Test
    @DisplayName("Deve retornar um boolean = true")
    void shouldReturnTrue(){
        //GIVEN
        Boolean isDeleted = true;
        //WHEN
        Boolean actualResponse = new RestaurantPresenter().execute(isDeleted);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um boolean = false")
    void shouldReturnFalse(){
        //GIVEN
        Boolean isDeleted = false;
        //WHEN
        Boolean actualResponse = new RestaurantPresenter().execute(isDeleted);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar uma lista de restaurant")
    void shouldReturnListAllRestaurants(){
        //GIVEN
        List<Restaurant> restaurants = List.of(
                this.restaurant,
                this.restaurant,
                this.restaurant,
                this.restaurant
        );
        List<RestaurantResponse> restaurantResponses = List.of(
                this.restaurantResponse,
                this.restaurantResponse,
                this.restaurantResponse,
                this.restaurantResponse
        );

        //WHEN
        List<RestaurantResponse> actualResponse = new RestaurantPresenter().execute(restaurants);

        //THEN
        assertEquals(restaurantResponses.size(), actualResponse.size());
    }



}
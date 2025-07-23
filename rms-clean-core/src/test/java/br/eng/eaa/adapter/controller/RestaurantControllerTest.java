package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IRestaurantGateway;
import br.eng.eaa.adapter.gateway.RestaurantGatewayStub;
import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Menu;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestaurantControllerTest {

    private IRestaurantGateway restaurantGateway;
    private RestaurantController restaurantController;
    private UUID id;
    private String name;
    private CuisineType cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private User owner;
    private Address address;
    private List<Menu> menus;
    private UUID restaurantId;

    private RestaurantRequest restaurantRequest;
    private RestaurantResponse restaurantResponse;

    @BeforeEach
    void setUp() {
        this.restaurantGateway = new RestaurantGatewayStub();
        this.restaurantController = new RestaurantController(restaurantGateway);
        this.id = UUID.fromString("31d21f36-6edc-4f3d-9fda-879e09c739fe");
        this.name = "Restaurante da Maria";
        this.restaurantId = UUID.randomUUID();
        this.openTime = LocalTime.of(11, 0);
        this.closeTime = LocalTime.of(22, 0);
        List<Role> roles = List.of(new Role("ADMIN"), new Role("OWNER"));
        List<Menu> menus = List.of(
                new Menu("Contra-filé ", "Contra-file com fritas, arroz branco e feijão", BigDecimal.valueOf(45.00), true, "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg", restaurantId),
                new Menu("Contra-filé a cavalo", "Contra-filé com ovo frito, arroz brando e feijão carioquinha",BigDecimal.valueOf(47.00), true, "https://sabores-new.s3.amazonaws.com/public/2024/11/bife-a-cavalo-1024x494.jpg", restaurantId)
        );
        Address address = new Address("Av. Paulista, 1106", "São Paulo", "SP", "01311-000");
        this.cuisineType = CuisineType.BRASILIAN;
        this.owner = new User (UUID.randomUUID(), "Eduardo",  roles);
        this.address = address;
        this.menus = menus;

        this.restaurantRequest = new RestaurantRequest(name, cuisineType, openTime, closeTime, owner, address, menus);
        this.restaurantResponse = new RestaurantResponse(id, name, cuisineType, openTime, closeTime, owner, address, menus);
    }

    @Test
    @DisplayName("Deve salvar um restaurante válido")
    void givenAValidUser_whenCallsSave_thenReturnSavedUser(){
        //GIVEN
        RestaurantRequest restaurantRequest = this.restaurantRequest;
        RestaurantResponse restaurantResponse = this.restaurantResponse;

        //WHEN
        RestaurantResponse actualRestaurant = restaurantController.save(restaurantRequest);

        //THEN
        assertNotNull(actualRestaurant.getId());
        assertEquals(restaurantResponse.getName(), actualRestaurant.getName());
        assertEquals(restaurantResponse.getCuisineType(), actualRestaurant.getCuisineType());
        assertEquals(restaurantResponse.getOwner().getUserName(), actualRestaurant.getOwner().getUserName());
        assertEquals(restaurantResponse.getAddress().getStreet(), actualRestaurant.getAddress().getStreet());
        assertEquals(restaurantResponse.getMenus().size(), actualRestaurant.getMenus().size());

    }

    @Test
    @DisplayName("Deve atualizar um restaurante válido")
    void givenAValidUser_whenCallsUpdate_thenReturnUpdatedUser(){
        //GIVEN
        RestaurantRequest restaurantRequest = this.restaurantRequest;
        RestaurantResponse restaurantResponse = this.restaurantResponse;

        //WHEN
        RestaurantResponse actualRestaurant = restaurantController.update(restaurantRequest);

        //THEN
        assertNotNull(actualRestaurant.getId());
        assertEquals(restaurantResponse.getName(), actualRestaurant.getName());
        assertEquals(restaurantResponse.getCuisineType(), actualRestaurant.getCuisineType());
        assertEquals(restaurantResponse.getOwner().getUserName(), actualRestaurant.getOwner().getUserName());
        assertEquals(restaurantResponse.getAddress().getStreet(), actualRestaurant.getAddress().getStreet());
        assertEquals(restaurantResponse.getMenus().size(), actualRestaurant.getMenus().size());

    }

    @Test
    @DisplayName("Deve retornar true na exclusão de um usuário com id válido")
    void givenAValidUserRequest_whenCallsDelete_thenReturnDeletedUserResponse() {
        //GIVEN
        Boolean isDeleted = true;
        UUID id = this.id;
        //WHEN
        Boolean actualResponse = restaurantController.delete(id);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar false na exclusão de um restaurante com id válido")
    void givenAInvalidURestaurantRequest_whenCallsDelete_thenReturnDeletedRestaurantResponse() {
        //GIVEN
        Boolean isDeleted = false;
        UUID id = UUID.randomUUID();
        //WHEN
        Boolean actualResponse = restaurantController.delete(id);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um restaurante quando pesquisar um id válido")
    void givenAValidRestaurantId_whenCallsFindById_thenReturnRestaurant() {
        //GIVEN
        UUID id = this.id;
        RestaurantResponse restaurantResponse = this.restaurantResponse;

        //WHEN
        RestaurantResponse actualRestaurant = restaurantController.findById(id);

        //THEN
        assertEquals(restaurantResponse.getId(), actualRestaurant.getId());
        assertEquals(restaurantResponse.getName(), actualRestaurant.getName());
        assertEquals(restaurantResponse.getCuisineType(), actualRestaurant.getCuisineType());
        assertEquals(restaurantResponse.getOwner().getUserName(), actualRestaurant.getOwner().getUserName());
        assertEquals(restaurantResponse.getAddress().getStreet(), actualRestaurant.getAddress().getStreet());
        assertEquals(restaurantResponse.getMenus().size(), actualRestaurant.getMenus().size());

    }

    @Test
    @DisplayName("Deve retornar uma lista de restaurantes")
    void givenAValidRestaurantRequest_whenCallsFindAll_thenReturnAListOfRestaurantResponse(){
        //GIVEN
        List<RestaurantResponse> restaurantResponses = List.of(this.restaurantResponse, this.restaurantResponse);

        //WHEN
        List<RestaurantResponse> actualResponse = restaurantController.findAll();

        //THEN
        assertEquals(restaurantResponses.size(), actualResponse.size());

    }




}
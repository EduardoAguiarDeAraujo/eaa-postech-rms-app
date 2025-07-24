package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IRestaurantGateway;
import br.eng.eaa.application.boundary.output.RestaurantOutputPort;
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
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RestaurantInteractorTest {

    private IRestaurantGateway restaurantGateway;
    private RestaurantInteractor restaurantInteractor;
    private RestaurantOutputPort restaurantOutput;

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
    void setUp(){
        this.restaurantGateway = Mockito.mock(IRestaurantGateway.class);
        this.restaurantOutput = Mockito.mock(RestaurantOutputPort.class);
        this.restaurantInteractor = new RestaurantInteractor(restaurantGateway, restaurantOutput);

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

        this.restaurantRequest = new RestaurantRequest(name, cuisineType, openTime, closeTime, owner, address, menus);
        this.restaurantResponse = new RestaurantResponse(id, name, cuisineType, openTime, closeTime, owner, address, menus);

    }

    @Test
    @DisplayName("Deve salvar um restaurante válido")
    void shouldSaveARestaurant() {

        //WHEN
        when(restaurantInteractor.save(restaurantRequest)).thenReturn(restaurantResponse);
        RestaurantResponse actualRestaurant = restaurantInteractor.save(restaurantRequest);

        //THEN
        assertEquals(restaurantResponse, actualRestaurant);
    }

    @Test
    @DisplayName("Deve atualizar um restaurante válido")
    void shouldUpdateARestaurant() {

        //WHEN
        when(restaurantInteractor.update(restaurantRequest)).thenReturn(restaurantResponse);
        RestaurantResponse actualRestaurant = restaurantInteractor.update(restaurantRequest);

        //THEN
        assertEquals(restaurantResponse, actualRestaurant);
    }

    @Test
    @DisplayName("Deve buscar um restaurant por id")
    void shouldFindARestaurantById() {

        //WHEN
        when(restaurantInteractor.update(restaurantRequest)).thenReturn(restaurantResponse);
        RestaurantResponse actualRestaurant = restaurantInteractor.update(restaurantRequest);

        //THEN
        assertEquals(restaurantResponse, actualRestaurant);
    }

    @Test
    @DisplayName("Deve excluir um restaurant por id")
    void shouldDeleteRestaurantById() {
        //WHEN
        when(restaurantInteractor.delete(id)).thenReturn(true);
        Boolean actualRestaurant = restaurantInteractor.delete(id);

        //THEN
        assertEquals(true, actualRestaurant);
    }


    @Test
    @DisplayName("Deve buscar uma lista de restaurantes")
    void shouldFindAListOfAllRestaurant() {
        //GIVEN
        List<RestaurantResponse> restaurantResponses = List.of(this.restaurantResponse, this.restaurantResponse);

        //WHEN
        when(restaurantInteractor.findAll()).thenReturn(restaurantResponses);
        List<RestaurantResponse>  actualRestaurants = restaurantInteractor.findAll();

        //THEN
        assertEquals(restaurantResponses, actualRestaurants);
    }



}
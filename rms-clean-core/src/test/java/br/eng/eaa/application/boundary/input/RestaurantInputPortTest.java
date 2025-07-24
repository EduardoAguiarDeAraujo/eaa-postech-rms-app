package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantInputPortTest {

    private RestaurantInputPort restaurantInputPort;
    private UUID id;
    private String name;
    private CuisineType cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private User owner;
    private Address address;
    private List<Menu> menus;
    private UUID restaurantId;


    @BeforeEach
    void setUp() {

        this.restaurantInputPort = Mockito.mock(RestaurantInputPort.class);
        this.id = UUID.randomUUID();
        this.name = "Restaurante da Maria";
        this.restaurantId = UUID.randomUUID();
        this.openTime = LocalTime.of(11, 0);
        this.closeTime = LocalTime.of(22, 0);
        List<Role> roles = List.of(new Role("ADMIN"), new Role("ADMIN"));
        List<Menu> menus = List.of(
                new Menu("Contra-filé ", "Contra-file com fritas, arroz branco e feijão", BigDecimal.valueOf(45.00), true, "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg", restaurantId),
                new Menu("Contra-filé a cavalo", "Contra-filé com ovo frito, arroz brando e feijão carioquinha",BigDecimal.valueOf(47.00), true, "https://sabores-new.s3.amazonaws.com/public/2024/11/bife-a-cavalo-1024x494.jpg", restaurantId)
        );
        Address address = new Address("Av. Paulista, 1106", "São Paulo", "SP", "01311-000");
        this.cuisineType = CuisineType.BRASILIAN;
        this.owner = new User (UUID.randomUUID(), "Eduardo",  roles);
        this.address = address;
        this.menus = menus;

    }

    @Test
    @DisplayName("Deve salvar um restaurante válido")
    void givenAValidRestaurant_whenCallsSave_thenReturnSavedRestaurant() {
        // GIVEN
        RestaurantRequest restaurantRequest = new RestaurantRequest(id, name, cuisineType, openTime, closeTime, owner, address, menus);
        RestaurantResponse restaurantResponse = new RestaurantResponse(id, name, cuisineType, openTime, closeTime, owner, address, menus);

        // WHEN
        when(restaurantInputPort.save(restaurantRequest)).thenReturn(restaurantResponse);
        RestaurantResponse actualRestaurant = restaurantInputPort.save(restaurantRequest);

        // THEN
        verify(restaurantInputPort, times(1)).save(restaurantRequest);
        assertEquals(restaurantResponse, actualRestaurant);
    }

}
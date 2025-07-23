package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IRestaurantGatewayTest {

    private IRestaurantGateway restaurantGateway;
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


    @BeforeEach
    void setUp() {

        this.restaurantGateway = Mockito.mock(IRestaurantGateway.class);
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

        this.restaurant = new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus);
    }

    @Test
    @DisplayName("Deve salvar um restaurante válido")
    void givenAValidRestaurant_whenCallsSave_thenReturnSavedRestaurant() {
        // GIVEN
        Restaurant aRestaurant = this.restaurant;
        Restaurant savedRestaurant = this.restaurant;

        // WHEN
        when(restaurantGateway.save(aRestaurant)).thenReturn(savedRestaurant);
        Restaurant actualRestaurant = restaurantGateway.save(aRestaurant);

        // THEN
        verify(restaurantGateway, times(1)).save(aRestaurant);
        assertEquals(savedRestaurant, actualRestaurant);
    }

    @Test
    @DisplayName("Deve atualizar um restaurante")
    void givenAValidRestaurant_whenCallsUpdate_thenReturnUpdatedRestaurant() {
        // GIVEN
        Restaurant updatedRestaurant = this.restaurant;

        // WHEN
        when(restaurantGateway.update(updatedRestaurant)).thenReturn(updatedRestaurant);
        Restaurant actualRestaurant = restaurantGateway.update(updatedRestaurant);

        // THEN
        verify(restaurantGateway, times(1)).update(updatedRestaurant);
        assertEquals(updatedRestaurant, actualRestaurant);
    }

    @Test
    @DisplayName("Deve retornar um restaurante por ID válido")
    void givenAValidId_whenCallsFindById_thenReturnRestaurant() {
        // GIVEN
        Restaurant expectedRestaurant = this.restaurant;

        // WHEN
        when(restaurantGateway.findById(id)).thenReturn(expectedRestaurant);
        Restaurant actualRestaurant = restaurantGateway.findById(id);

        // THEN
        verify(restaurantGateway, times(1)).findById(id);
        assertEquals(expectedRestaurant, actualRestaurant);
    }

    @Test
    @DisplayName("Deve retornar null para ID inválido")
    void givenAnInvalidId_whenCallsFindById_thenReturnNull() {
        // GIVEN
        UUID invalidId = UUID.randomUUID();

        // WHEN
        when(restaurantGateway.findById(invalidId)).thenReturn(null);
        Restaurant actualRestaurant = restaurantGateway.findById(invalidId);

        // THEN
        verify(restaurantGateway, times(1)).findById(invalidId);
        assertNull(actualRestaurant);
    }

    @Test
    @DisplayName("Deve retornar uma lista de restaurantes")
    void whenCallsFindAll_thenReturnAllRestaurants() {
        // GIVEN
        List<Restaurant> expectedRestaurants = Arrays.asList(
                this.restaurant,
                this.restaurant
        );

        // WHEN
        when(restaurantGateway.findAll()).thenReturn(expectedRestaurants);
        List<Restaurant> actualRestaurants = restaurantGateway.findAll();

        // THEN
        verify(restaurantGateway, times(1)).findAll();
        assertEquals(expectedRestaurants.size(), actualRestaurants.size());
        assertTrue(actualRestaurants.containsAll(expectedRestaurants));
    }

    @Test
    @DisplayName("Deve retornar true para exclusão de um ID válido")
    void givenAValidId_whenCallsDelete_thenReturnTrue() {
        // GIVEN
        UUID idToDelete = UUID.randomUUID();

        // WHEN
        when(restaurantGateway.delete(idToDelete)).thenReturn(true);
        Boolean actualResult = restaurantGateway.delete(idToDelete);

        // THEN
        verify(restaurantGateway, times(1)).delete(idToDelete);
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Deve retornar false para exclusão de um ID inválido")
    void givenAnInvalidId_whenCallsDelete_thenReturnFalse() {
        // GIVEN
        UUID invalidId = UUID.randomUUID();

        // WHEN
        when(restaurantGateway.delete(invalidId)).thenReturn(false);
        Boolean actualResult = restaurantGateway.delete(invalidId);

        // THEN
        verify(restaurantGateway, times(1)).delete(invalidId);
        assertFalse(actualResult);
    }

}
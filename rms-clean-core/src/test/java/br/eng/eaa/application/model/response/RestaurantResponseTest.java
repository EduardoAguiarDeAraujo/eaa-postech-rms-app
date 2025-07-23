package br.eng.eaa.application.model.response;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantResponseTest {

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
        this.id = UUID.randomUUID();
        this.name = "Restaurante da Maria";
        this.openTime = LocalTime.parse("11:00");
        this.closeTime = LocalTime.parse("22:00");
        this.restaurantId = UUID.randomUUID();
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
    }

    @Test
    @DisplayName("Deve retornar um restaurante válido")
    void givenValidData_whenConstructingRestaurantResponse_thenAllFieldsAreSetCorrectly() {
        RestaurantResponse restaurantResponse = new RestaurantResponse(id, name, cuisineType, openTime, closeTime,  owner, address, menus);

        assertEquals(id, restaurantResponse.getId());
        assertEquals(name, restaurantResponse.getName());
        assertEquals(cuisineType, restaurantResponse.getCuisineType());
        assertEquals(openTime, restaurantResponse.getOpenTime());
        assertEquals(closeTime, restaurantResponse.getCloseTime());
        assertEquals(owner, restaurantResponse.getOwner());
        assertEquals(address, restaurantResponse.getAddress());
        assertEquals(menus.size(), restaurantResponse.getMenus().size());
        assertTrue(restaurantResponse.getMenus().containsAll(menus));

        System.out.printf("RestaurantResponse - id: %s, nome: %s %n", restaurantResponse.getId(), restaurantResponse.getName());
    }



}
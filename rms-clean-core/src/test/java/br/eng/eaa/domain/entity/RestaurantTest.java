package br.eng.eaa.domain.entity;


import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

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
        this.restaurantId = UUID.randomUUID();
        this.openTime = LocalTime.parse("11:00");
        this.closeTime = LocalTime.parse("22:00");
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
    @DisplayName("Deve criar um restaurante válido construtor 1")
    void shouldCreateValidRestaurant1(){
        Restaurant restaurant = new Restaurant(id, name, cuisineType, openTime, closeTime,  owner, address, menus);

        assertNotNull(restaurant);
        assertEquals(id, restaurant.getId());
        assertEquals(name, restaurant.getName());
        assertEquals(cuisineType, restaurant.getCuisineType());
        assertEquals(owner, restaurant.getOwner());
        assertEquals(address, restaurant.getAddress());
        assertEquals(menus, restaurant.getMenus());

        System.out.printf("Restaurante válido - id: %s, nome: %s, tipo de cozinha: %s, proprietário: %s %n", restaurant.getId(), restaurant.getName(), restaurant.getCuisineType(), restaurant.getOwner().getUserName() );
    }

    @Test
    @DisplayName("Deve criar um restaurante válido construtor 2")
    void shouldCreateValidRestaurant2(){
        Restaurant restaurant = new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus);

        assertNotNull(restaurant);
        assertNotNull(restaurant.getId());
        assertEquals(name, restaurant.getName());
        assertEquals(cuisineType, restaurant.getCuisineType());
        assertEquals(owner, restaurant.getOwner());
        assertEquals(address, restaurant.getAddress());
        assertEquals(menus, restaurant.getMenus());

        System.out.printf("Restaurante válido - id: %s, nome: %s, tipo de cozinha: %s, proprietário: %s %n", restaurant.getId(), restaurant.getName(), restaurant.getCuisineType(), restaurant.getOwner().getUserName() );
    }

    @Test
    @DisplayName("Deve criar um restaurante válido construtor 4")
    void shouldCreateValidRestaurant4(){
        Restaurant restaurant = new Restaurant(id, name);

        assertNotNull(restaurant);
        assertNotNull(restaurant.getId());
        assertEquals(name, restaurant.getName());

        System.out.printf("Restaurante válido - id: %s, nome: %s %n", restaurant.getId(), restaurant.getName() );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 1")
    void shouldReturnExceptionInvalidParameterContructor1(){
        UUID id = null;
        String name = null;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 1")
    void shouldReturnExceptionInvalidParameterContructor1_2(){
        UUID id = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 1")
    void shouldReturnExceptionInvalidParameterContructor1_3(){
        String name = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 2")
    void shouldReturnExceptionInvalidParameterContructor2(){
        UUID id = null;
        String name = null;
        CuisineType cuisineType = null;
        User owner = null;
        Address address = null;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3(){
        UUID id = null;
        String name = null;
        CuisineType cuisineType = null;
        User owner = null;
        Address address = null;
        List<Menu> menus = null;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_2(){
        UUID id = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }
    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_3(){
        String name = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus) );
    }
    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_4(){
        CuisineType cuisineType = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus) );
    }
    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_5(){
        User owner = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_6(){
        Address address = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_7(){
        List<Menu> menus = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_8(){
        String name = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_9(){
        CuisineType cuisineType = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_10(){
        User owner = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_11(){
        Address address = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_12(){
        List<Menu> menus = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_13(){
        String name = "";
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_14(){
        String name = "  ";
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }

}
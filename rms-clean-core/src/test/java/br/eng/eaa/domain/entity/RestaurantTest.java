package br.eng.eaa.domain.entity;


import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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
        this.menus = List.of(
            new Menu("Contra-filé ", "Contra-file com fritas, arroz branco e feijão", BigDecimal.valueOf(45.00), true, "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg", restaurantId),
            new Menu("Contra-filé a cavalo", "Contra-filé com ovo frito, arroz brando e feijão carioquinha",BigDecimal.valueOf(47.00), true, "https://sabores-new.s3.amazonaws.com/public/2024/11/bife-a-cavalo-1024x494.jpg", restaurantId)
        );
        this.address = new Address("Av. Paulista, 1106", "São Paulo", "SP", "01311-000");
        this.cuisineType = CuisineType.BRASILIAN;
        this.owner = new User (UUID.randomUUID(), "Eduardo",  roles);
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
        UUID idNull = null;
        String nameNull = null;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(idNull, nameNull) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 1")
    void shouldReturnExceptionInvalidParameterContructor1_2(){
        UUID idNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(idNull, name) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 1")
    void shouldReturnExceptionInvalidParameterContructor1_3(){
        String nameNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, nameNull) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 2")
    void shouldReturnExceptionInvalidParameterContructor2(){
        UUID idNull = null;
        String nameNull = null;
        CuisineType cuisineTypeNull = null;
        User ownerNull = null;
        Address addressNull = null;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(idNull, nameNull, cuisineTypeNull, openTime, closeTime, ownerNull, addressNull, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3(){
        String nameNull = null;
        CuisineType cuisineTypeNull = null;
        User ownerNull = null;
        Address addressNull = null;
        List<Menu> menusNull = null;

        assertThrows(IllegalArgumentException.class, () -> new Restaurant(nameNull, cuisineTypeNull, openTime, closeTime, ownerNull, addressNull, menusNull) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_2(){
        UUID idNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(idNull, name, cuisineType, openTime, closeTime, owner, address, menus) );
    }
    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_3(){
        String nameNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(nameNull, cuisineType, openTime, closeTime, owner, address, menus) );
    }
    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_4(){
        CuisineType cuisineTypeNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineTypeNull, openTime, closeTime, owner, address, menus) );
    }
    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_5(){
        User ownerNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, ownerNull, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_6(){
        Address addressNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, addressNull, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_7(){
        List<Menu> menusNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(name, cuisineType, openTime, closeTime, owner, address, menusNull) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_9(){
        CuisineType cuisineTypeNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineTypeNull, openTime, closeTime, owner, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_10(){
        User ownerNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, ownerNull, address, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_11(){
        Address addressNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, addressNull, menus) );
    }

    @Test
    @DisplayName("Deve retornar um exception quando parâmetros invalidos construtor 3")
    void shouldReturnExceptionInvalidParameterContructor3_12(){
        List<Menu> menusNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menusNull) );
    }

    @ParameterizedTest
    @NullAndEmptySource // Fornece null e "" (string vazia) para 'name'
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Fornece strings em branco (espaços, tabs, quebras de linha) para 'name'
    @DisplayName("Deve retornar uma exceção quando o nome é nulo, vazio ou em branco no construtor 3")
    void shouldReturnExceptionWhenNameIsNullOrEmptyOrBlankConstructor3(String name) {
        assertThrows(IllegalArgumentException.class, () ->
                new Restaurant(id, name, cuisineType, openTime, closeTime, owner, address, menus));
    }
}
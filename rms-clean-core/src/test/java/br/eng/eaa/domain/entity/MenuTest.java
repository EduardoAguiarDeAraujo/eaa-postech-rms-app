package br.eng.eaa.domain.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurant;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.name = "Contra-filé com fritas";
        this.description = "Contra-filé com fritas, arroz branco e feijão carioquinha";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurant = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve retornar um menu válido construtor 1")
    void shouldReturnValidMenu1() {
        Menu menu = new Menu(id, name, description, price, available, imageUrl,restaurant);

        assertNotNull(menu);
        assertEquals(id, menu.getId());
        assertEquals(name, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(price, menu.getPrice());
        assertEquals(available, menu.getAvailable());
        assertEquals(imageUrl, menu.getImageUrl());

        System.out.printf("Menu válido - id: %s, nome: %s %n", menu.getId(), menu.getName() );
    }

    @Test
    @DisplayName("Deve retornar um menu válido construtor 3")
    void shouldReturnValidMenu3() {
        Menu menu = new Menu(name, description, price, available, imageUrl, restaurant);

        assertNotNull(menu);
        assertNotNull(menu.getId());
        assertEquals(name, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(price, menu.getPrice());
        assertEquals(available, menu.getAvailable());
        assertEquals(imageUrl, menu.getImageUrl());
        assertEquals(restaurant, menu.getRestaurantId());

        System.out.printf("Menu válido - id: %s, nome: %s %n", menu.getId(), menu.getName() );
    }

    @Test
    @DisplayName("Deve retornar um exception quando name = null")
    void shouldReturnExceptionMenuNameNull() {
        String nameNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(nameNull, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando name = empty")
    void shouldReturnExceptionMenuNameEmpty() {
        String nameEmpty = "";
        assertThrows(IllegalArgumentException.class, () -> new Menu(nameEmpty, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando name = blank")
    void shouldReturnExceptionMenuNameBlank() {
        String nameBlank = " ";
        assertThrows(IllegalArgumentException.class, () -> new Menu(nameBlank, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando description = null")
    void shouldReturnExceptionMenuDescriptionNull() {
        String description = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando description = empty")
    void shouldReturnExceptionMenuDescriptionEmpty() {
        String description = "";
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando description = blank")
    void shouldReturnExceptionMenuDescriptionBlank() {
        String description = " ";
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando imageUrl = null")
    void shouldReturnExceptionMenuImageURLNull() {
        String imageUrl = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando imageUrl = empty")
    void shouldReturnExceptionMenuImageURLEmpty() {
        String imageUrl = "";
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando imageUrl = blank")
    void shouldReturnExceptionMenuImageURLBlank() {
        String imageUrl = " ";
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando price = null")
    void shouldReturnExceptionMenuPriceNull() {
        BigDecimal price = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando restaurant = null")
    void shouldReturnExceptionMenuRestaurantNull() {
        UUID restaurant = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando available = null")
    void shouldReturnExceptionMenuAvailableNull() {
        Boolean available = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando id = null")
    void shouldReturnExceptionMenuIDNull() {
        UUID id = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(id, name, description, price, available, imageUrl, restaurant));
    }



}
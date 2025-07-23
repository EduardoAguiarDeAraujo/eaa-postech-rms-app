package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuEntityTest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private RestaurantEntity restaurant;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "Test Menu";
        description = "Test Description";
        price = BigDecimal.valueOf(10.99);
        available = true;
        imageUrl = "http://example.com/image.jpg";
        restaurant = new RestaurantEntity(UUID.randomUUID(), "Restaurante da Maria");
    }

    @Test
    @DisplayName("Deve retornar um MenuEntity válido")
    void shouldReturnValidMenuEntity() {
        MenuEntity menu = new MenuEntity(id, name, description, price, available, imageUrl, restaurant);

        assertNotNull(menu);
        assertEquals(id, menu.getId());
        assertEquals(name, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(price, menu.getPrice());
        assertEquals(available, menu.getAvailable());
        assertEquals(imageUrl, menu.getImageUrl());
        assertEquals(restaurant, menu.getRestaurant());

        System.out.printf("Menu válido - Id: %s - %s %n", menu.getId(), menu.getName());
    }

    @Test
    @DisplayName("Deve criar um MenuEntity pelo construtor vazio")
    void shouldCreateMenuEntityWithEmptyConstructor() {
        MenuEntity menu = new MenuEntity();
        menu.setId(id);
        menu.setName(name);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setAvailable(available);
        menu.setImageUrl(imageUrl);
        menu.setRestaurant(restaurant);

        assertNotNull(menu);
        assertEquals(id, menu.getId());
        assertEquals(name, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(price, menu.getPrice());
        assertEquals(available, menu.getAvailable());
        assertEquals(imageUrl, menu.getImageUrl());
        assertEquals(restaurant, menu.getRestaurant());

        System.out.printf("Menu criado pelo construtor vazio - Id: %s - %s %n", menu.getId(), menu.getName());
    }




}
package br.eng.eaa.domain.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @NullAndEmptySource // Fornece null e "" (string vazia)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Fornece strings em branco (espaços, tabs, quebras de linha)
    @DisplayName("Deve retornar uma exceção quando o nome é nulo, vazio ou em branco")
    void shouldReturnExceptionWhenNameIsNullOrEmptyOrBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @ParameterizedTest
    @NullAndEmptySource // Cobre casos com description = null e description = ""
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Cobre casos com description em branco
    @DisplayName("Deve retornar uma exceção quando description é nulo, vazio ou em branco")
    void shouldReturnExceptionWhenDescriptionIsNullOrEmptyOrBlank(String description) {
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @ParameterizedTest
    @NullAndEmptySource // Cobre null e ""
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Cobre strings em branco (espaços, tabs, novas linhas)
    @DisplayName("Deve retornar uma exceção quando imageUrl é nulo, vazio ou em branco")
    void shouldReturnExceptionWhenImageUrlIsNullOrEmptyOrBlank(String imageUrl) {
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando price = null")
    void shouldReturnExceptionMenuPriceNull() {
        BigDecimal priceNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, priceNull, available, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando restaurant = null")
    void shouldReturnExceptionMenuRestaurantNull() {
        UUID restaurantNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, available, imageUrl, restaurantNull));
    }

    @Test
    @DisplayName("Deve retornar um exception quando available = null")
    void shouldReturnExceptionMenuAvailableNull() {
        Boolean availableNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, description, price, availableNull, imageUrl, restaurant));
    }

    @Test
    @DisplayName("Deve retornar um exception quando id = null")
    void shouldReturnExceptionMenuIDNull() {
        UUID idNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Menu(idNull, name, description, price, available, imageUrl, restaurant));
    }



}
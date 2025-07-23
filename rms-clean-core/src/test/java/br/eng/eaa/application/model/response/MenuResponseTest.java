package br.eng.eaa.application.model.response;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuResponseTest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp(){
        this.id = UUID.randomUUID();
        this.name = "Contra-filé acebolado";
        this.description = "Contra-filé acebolado com arroz branco e feijão";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve retornar um menu válido")
    void shouldReturnMenuValid(){
        Menu menu = new Menu(id,name,description,price,available,imageUrl, restaurantId);

        assertNotNull(menu);
        assertEquals(id, menu.getId());
        assertEquals(name, menu.getName());
        assertEquals(description, menu.getDescription());
        assertEquals(price, menu.getPrice());
        assertEquals(available, menu.getAvailable());
        assertEquals(imageUrl, menu.getImageUrl());
        assertEquals(restaurantId, menu.getRestaurantId());

        System.out.printf("MenuResponse válido - id: %s, name: %s", menu.getId(), menu.getName() );

    }


}
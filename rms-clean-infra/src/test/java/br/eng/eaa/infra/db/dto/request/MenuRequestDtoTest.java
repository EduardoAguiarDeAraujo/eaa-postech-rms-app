package br.eng.eaa.infra.db.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuRequestDtoTest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @Test
    @DisplayName("Test MenuRequestDto creation")
    void testMenuRequestDtoCreation() {
        // Given
        id = UUID.randomUUID();
        name = "Test Menu";
        description = "Test Description";
        price = BigDecimal.valueOf(10.99);
        available = true;
        imageUrl = "http://example.com/image.jpg";
        restaurantId = UUID.randomUUID();

        // When
        var menuRequestDto = new MenuRequestDto(id, name, description,price,available,imageUrl, restaurantId);

        // Then
        assertEquals(id, menuRequestDto.id());
        assertEquals(name, menuRequestDto.name());
        assertEquals(description, menuRequestDto.description());
        assertEquals(price, menuRequestDto.price());
        assertEquals(available, menuRequestDto.available());
        assertEquals(imageUrl, menuRequestDto.imageUrl());
        assertEquals(restaurantId, menuRequestDto.restaurantId());
    }

}
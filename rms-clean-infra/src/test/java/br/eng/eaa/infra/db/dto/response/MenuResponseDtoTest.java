package br.eng.eaa.infra.db.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuResponseDtoTest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @Test
    @DisplayName("Test MenuResponseDto creation")
    void testMenuResponseDtoCreation() {
        // Given
        id = UUID.randomUUID();
        name = "Test Menu";
        description = "Test Description";
        price = BigDecimal.valueOf(10.99);
        available = true;
        imageUrl = "http://example.com/image.jpg";
        restaurantId = UUID.randomUUID();

        // When
        var menuResponseDto = new MenuResponseDto(id, name, description, price, available, imageUrl, restaurantId);

        // Then
        assertEquals(id, menuResponseDto.id());
        assertEquals(name, menuResponseDto.name());
        assertEquals(description, menuResponseDto.description());
        assertEquals(price, menuResponseDto.price());
        assertEquals(available, menuResponseDto.available());
        assertEquals(imageUrl, menuResponseDto.imageUrl());
        assertEquals(restaurantId, menuResponseDto.restaurantId());
    }


}
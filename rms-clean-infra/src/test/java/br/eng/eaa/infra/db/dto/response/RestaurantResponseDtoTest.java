package br.eng.eaa.infra.db.dto.response;

import br.eng.eaa.infra.db.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantResponseDtoTest {

    private UUID id;
    private String name;
    private CuisineTypeEnum cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private UserResponseDto owner;
    private AddressEntity address;
    private List<MenuResponseDto> menus;

    @Test
    @DisplayName("Test RestaurantResponseDto creation")
    void testRestaurantResponseDtoCreation() {
        // Given
        id = UUID.randomUUID();
        name = "Test Restaurant";
        cuisineType = CuisineTypeEnum.ITALIAN;
        owner = new UserResponseDto(UUID.randomUUID(), "Eduardo",  List.of());
        address = new AddressEntity("123 Main St", "City", "State", "12345");
        menus = List.of(new MenuResponseDto(UUID.randomUUID(),
                "Contra-filé",
                "Description 1",
                BigDecimal.valueOf(29.99),
                true,
                "http://example.com/image.jpg",
                UUID.randomUUID()));

        // When
        var restaurantResponseDto = new RestaurantResponseDto(id, name, cuisineType, openTime, closeTime, owner, address, menus);

        // Then
        assertEquals(id, restaurantResponseDto.id());
        assertEquals(name, restaurantResponseDto.name());
        assertEquals(cuisineType, restaurantResponseDto.cuisineType());
        assertEquals(owner.id(), restaurantResponseDto.owner().id());
        assertEquals(address.getStreet(), restaurantResponseDto.address().getStreet());
        assertEquals(menus.size(), restaurantResponseDto.menus().size());
    }

}
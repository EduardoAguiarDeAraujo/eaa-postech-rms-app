package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.infra.db.dto.request.MenuRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.db.entity.MenuEntity;
import br.eng.eaa.infra.db.entity.RestaurantEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class MenuMapperTest {

    @Mock
    private RestaurantEntity mockRestaurantEntity;

    @Mock
    private RestaurantMapper mockRestaurantMapper;

    private UUID menuId;
    private String menuName;
    private String menuDescription;
    private BigDecimal menuPrice;
    private Boolean menuAvailable;
    private String menuImageUrl;
    private UUID restaurantId;
    private RestaurantEntity restaurant;


    @BeforeEach
    void setUp() {
        mockRestaurantEntity = mock(RestaurantEntity.class);
        menuId = UUID.randomUUID();
        menuName = "Pizza Margherita";
        menuDescription = "Delicious classic pizza";
        menuPrice = new BigDecimal("45.00");
        menuAvailable = true;
        menuImageUrl = "http://example.com/pizza.jpg";
        restaurantId = UUID.randomUUID();
        restaurant = new RestaurantEntity(restaurantId, "Restaurante da Maria");
    }

    @Test
    @DisplayName("Should correctly map Menu to MenuEntity")
    void shouldMapMenuToMenuEntity() {
        try (MockedStatic<RestaurantMapper> mockedStatic = mockStatic(RestaurantMapper.class)) {
            // Given
            Menu menu = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

            // When
            MenuEntity menuEntity = MenuMapper.toEntity(menu);

            // Then
            assertNotNull(menuEntity);
            assertEquals(menuId, menuEntity.getId());
            assertEquals(menuName, menuEntity.getName());
            assertEquals(menuDescription, menuEntity.getDescription());
            assertEquals(menuPrice, menuEntity.getPrice());
            assertEquals(menuAvailable, menuEntity.getAvailable());
            assertEquals(menuImageUrl, menuEntity.getImageUrl());
        }
    }

    @Test
    @DisplayName("Deve mapear corretamento de MenuRequestDto para MenuRequest com id null")
    void shouldMapMenuRequestDtoToMenuRequestWithoutId() {
        try (MockedStatic<RestaurantMapper> mockedStatic = mockStatic(RestaurantMapper.class)) {
            // Given
            MenuRequestDto menuRequestDto = new MenuRequestDto(null, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

            // When
            MenuRequest menuRequest = MenuMapper.toRequest(menuRequestDto);

            // Then
            assertNotNull(menuRequest);
            assertNull(menuRequest.getId());
            assertEquals(menuName, menuRequest.getName());
            assertEquals(menuDescription, menuRequest.getDescription());
            assertEquals(menuPrice, menuRequest.getPrice());
            assertEquals(menuAvailable, menuRequest.getAvailable());
            assertEquals(menuImageUrl, menuRequest.getImageUrl());
            assertEquals(restaurantId, menuRequest.getRestaurantId());
        }
    }


    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null Menu to MenuEntity")
    void shouldThrowExceptionWhenMappingNullMenuToMenuEntity() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MenuMapper.toEntity(null));
        assertEquals("Menu must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should correctly map MenuEntity to Menu domain")
    void shouldMapMenuEntityToMenuDomain() {
        try (MockedStatic<RestaurantMapper> mockedStatic = mockStatic(RestaurantMapper.class)) {
            // Given
            MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurant);

            // When
            Menu menu = MenuMapper.toDomain(menuEntity);

            // Then
            assertNotNull(menu);
            assertEquals(menuId, menu.getId());
            assertEquals(menuName, menu.getName());
            assertEquals(menuDescription, menu.getDescription());
            assertEquals(menuPrice, menu.getPrice());
            assertEquals(menuAvailable, menu.getAvailable());
            assertEquals(menuImageUrl, menu.getImageUrl());
            assertEquals(restaurant.getId(), menu.getRestaurantId());
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null MenuEntity to Menu domain")
    void shouldThrowExceptionWhenMappingNullMenuEntityToMenuDomain() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MenuMapper.toDomain(null));
        assertEquals("MenuEntity must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should correctly map MenuRequestDto to MenuRequest")
    void shouldMapMenuRequestDtoToMenuRequest() {
        try (MockedStatic<RestaurantMapper> mockedStatic = mockStatic(RestaurantMapper.class)) {
            // Given
            MenuRequestDto menuRequestDto = new MenuRequestDto(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

            // When
            MenuRequest menuRequest = MenuMapper.toRequest(menuRequestDto);

            // Then
            assertNotNull(menuRequest);
            assertEquals(menuId, menuRequest.getId());
            assertEquals(menuName, menuRequest.getName());
            assertEquals(menuDescription, menuRequest.getDescription());
            assertEquals(menuPrice, menuRequest.getPrice());
            assertEquals(menuAvailable, menuRequest.getAvailable());
            assertEquals(menuImageUrl, menuRequest.getImageUrl());
            assertEquals(restaurantId, menuRequest.getRestaurantId());
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null MenuRequestDto to MenuRequest")
    void shouldThrowExceptionWhenMappingNullMenuRequestDtoToMenuRequest() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MenuMapper.toRequest(null));
        assertEquals("MenuRequestDto must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should correctly map MenuResponse to MenuResponseDto")
    void shouldMapMenuResponseToMenuResponseDto() {
        try (MockedStatic<RestaurantMapper> mockedStatic = mockStatic(RestaurantMapper.class)) {
            // Given
            MenuResponse menuResponse = new MenuResponse(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

            // When
            MenuResponseDto menuResponseDto = MenuMapper.toDto(menuResponse);

            // Then
            assertNotNull(menuResponseDto);
            assertEquals(menuId, menuResponseDto.id());
            assertEquals(menuName, menuResponseDto.name());
            assertEquals(menuDescription, menuResponseDto.description());
            assertEquals(menuPrice, menuResponseDto.price());
            assertEquals(menuAvailable, menuResponseDto.available());
            assertEquals(menuImageUrl, menuResponseDto.imageUrl());
            assertEquals(restaurantId, menuResponseDto.restaurantId());

        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null MenuResponse to MenuResponseDto")
    void shouldThrowExceptionWhenMappingNullMenuResponseToMenuResponseDto() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MenuMapper.toDto(null));
        assertEquals("MenuResponse must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should map Menu domain to MenuResponseDto")
    void shouldMapMenuDomainToMenuResponseDto() {
        try (MockedStatic<RestaurantMapper> mockedStatic = mockStatic(RestaurantMapper.class)) {
            // Given
            Menu menu = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

            // When
            MenuResponseDto menuResponseDto = MenuMapper.fromDomainToDto(menu);

            // Then
            assertNotNull(menuResponseDto);
            assertEquals(menuId, menuResponseDto.id());
            assertEquals(menuName, menuResponseDto.name());
            assertEquals(menuDescription, menuResponseDto.description());
            assertEquals(menuPrice, menuResponseDto.price());
            assertEquals(menuAvailable, menuResponseDto.available());
            assertEquals(menuImageUrl, menuResponseDto.imageUrl());
            assertEquals(restaurantId, menuResponseDto.restaurantId());
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null Menu domain to MenuResponseDto")
    void shouldThrowExceptionWhenMappingNullMenuDomainToMenuResponseDto() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> MenuMapper.fromDomainToDto(null));
        assertEquals("MenuResponse must be not null", exception.getMessage());
    }


}
package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import br.eng.eaa.infra.db.dto.request.RestaurantRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.db.dto.response.RestaurantResponseDto;
import br.eng.eaa.infra.db.dto.response.UserResponseDto;
import br.eng.eaa.infra.db.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantMapperTest {

    @Mock
    private User mockUser;
    @Mock
    private UserEntity mockUserEntity;
    @Mock
    private UserResponseDto mockUserResponseDto;
    @Mock
    private Address mockAddress;
    @Mock
    private AddressEntity mockAddressEntity;
    @Mock
    private Menu mockMenu;
    @Mock
    private MenuEntity mockMenuEntity;
    @Mock
    private MenuResponseDto mockMenuResponseDto;

    private UUID restaurantId;
    private String restaurantName;
    private CuisineType cuisineType;
    private CuisineTypeEnum cuisineTypeEnum;
    private LocalTime openTime;
    private LocalTime closeTime;

    @BeforeEach
    void setUp() {
        restaurantId = UUID.randomUUID();
        restaurantName = "Awesome Restaurant";
        cuisineType = CuisineType.ITALIAN;
        cuisineTypeEnum = CuisineTypeEnum.ITALIAN;
        openTime = LocalTime.of(11, 0);
        closeTime = LocalTime.of(22, 0);
    }


    @Test
    @DisplayName("Should correctly map Restaurant to RestaurantEntity")
    void shouldMapRestaurantToRestaurantEntity() {
        try (MockedStatic<UserMapper> userMapperMockedStatic = mockStatic(UserMapper.class);
             MockedStatic<AddressMapper> addressMapperMockedStatic = mockStatic(AddressMapper.class);
             MockedStatic<MenuMapper> menuMapperMockedStatic = mockStatic(MenuMapper.class)) {

            // Given
            List<Menu> menus = Collections.singletonList(mockMenu);
            Restaurant restaurant = new Restaurant(restaurantId, restaurantName, cuisineType, openTime, closeTime, mockUser, mockAddress, menus);

            when(UserMapper.toEntity(mockUser)).thenReturn(mockUserEntity);
            when(AddressMapper.toEntity(mockAddress)).thenReturn(mockAddressEntity);
            when(MenuMapper.toEntity(mockMenu)).thenReturn(mockMenuEntity);

            // When
            RestaurantEntity restaurantEntity = RestaurantMapper.toEntity(restaurant);

            // Then
            assertNotNull(restaurantEntity);
            assertEquals(restaurantId, restaurantEntity.getId());
            assertEquals(restaurantName, restaurantEntity.getName());
            assertEquals(cuisineTypeEnum, restaurantEntity.getCuisineType());
            assertEquals(mockUserEntity, restaurantEntity.getOwner());
            assertEquals(mockAddressEntity, restaurantEntity.getAddress());
            assertNotNull(restaurantEntity.getMenus());
            assertEquals(1, restaurantEntity.getMenus().size());
            assertEquals(mockMenuEntity, restaurantEntity.getMenus().get(0));

            userMapperMockedStatic.verify(() -> UserMapper.toEntity(mockUser), times(1));
            addressMapperMockedStatic.verify(() -> AddressMapper.toEntity(mockAddress), times(1));
            menuMapperMockedStatic.verify(() -> MenuMapper.toEntity(mockMenu), times(1));
        }
    }


    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null Restaurant to RestaurantEntity")
    void shouldThrowExceptionWhenMappingNullRestaurantToRestaurantEntity() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RestaurantMapper.toEntity(null));
        assertEquals("Restaurant must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should correctly map RestaurantEntity to Restaurant domain")
    void shouldMapRestaurantEntityToRestaurantDomain() {
        try (MockedStatic<UserMapper> userMapperMockedStatic = mockStatic(UserMapper.class);
             MockedStatic<AddressMapper> addressMapperMockedStatic = mockStatic(AddressMapper.class);
             MockedStatic<MenuMapper> menuMapperMockedStatic = mockStatic(MenuMapper.class)) {

            // Given
            List<MenuEntity> menuEntities = Collections.singletonList(mockMenuEntity);
            RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, cuisineTypeEnum, openTime, closeTime, mockUserEntity, mockAddressEntity, menuEntities);

            when(UserMapper.toDomain(mockUserEntity)).thenReturn(mockUser);
            when(AddressMapper.toDomain(mockAddressEntity)).thenReturn(mockAddress);
            when(MenuMapper.toDomain(mockMenuEntity)).thenReturn(mockMenu);

            // When
            Restaurant restaurant = RestaurantMapper.toDomain(restaurantEntity);

            // Then
            assertNotNull(restaurant);
            assertEquals(restaurantId, restaurant.getId());
            assertEquals(restaurantName, restaurant.getName());
            assertEquals(cuisineType, restaurant.getCuisineType());
            assertEquals(mockUser, restaurant.getOwner());
            assertEquals(mockAddress, restaurant.getAddress());
            assertNotNull(restaurant.getMenus());
            assertEquals(1, restaurant.getMenus().size());

            userMapperMockedStatic.verify(() -> UserMapper.toDomain(mockUserEntity), times(1));
            addressMapperMockedStatic.verify(() -> AddressMapper.toDomain(mockAddressEntity), times(1));
            menuMapperMockedStatic.verify(() -> MenuMapper.toDomain(mockMenuEntity), times(1));
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null RestaurantEntity to Restaurant domain")
    void shouldThrowExceptionWhenMappingNullRestaurantEntityToRestaurantDomain() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RestaurantMapper.toDomain(null));
        assertEquals("RestaurantEntity must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should correctly map RestaurantRequestDto to RestaurantRequest")
    void shouldMapRestaurantRequestDtoToRestaurantRequest() {
        try (MockedStatic<UserMapper> userMapperMockedStatic = mockStatic(UserMapper.class);
             MockedStatic<AddressMapper> addressMapperMockedStatic = mockStatic(AddressMapper.class);
             MockedStatic<MenuMapper> menuMapperMockedStatic = mockStatic(MenuMapper.class)) {

            // Given
            List<MenuEntity> menuEntities = Collections.singletonList(mockMenuEntity);
            RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto(restaurantId, restaurantName, cuisineTypeEnum, openTime, closeTime, mockUserEntity, mockAddressEntity, menuEntities);

            when(UserMapper.toDomain(mockUserEntity)).thenReturn(mockUser);
            when(AddressMapper.toDomain(mockAddressEntity)).thenReturn(mockAddress);
            when(MenuMapper.toDomain(mockMenuEntity)).thenReturn(mockMenu);

            // When
            RestaurantRequest restaurantRequest = RestaurantMapper.toRequest(restaurantRequestDto);

            // Then
            assertNotNull(restaurantRequest);
            assertEquals(restaurantId, restaurantRequest.getId());
            assertEquals(restaurantName, restaurantRequest.getName());
            assertEquals(cuisineType, restaurantRequest.getCuisineType());
            assertEquals(mockUser, restaurantRequest.getOwner());
            assertEquals(mockAddress, restaurantRequest.getAddress());
            assertNotNull(restaurantRequest.getMenus());
            assertEquals(mockMenu, restaurantRequest.getMenus().get(0));

            userMapperMockedStatic.verify(() -> UserMapper.toDomain(mockUserEntity), times(1));
            addressMapperMockedStatic.verify(() -> AddressMapper.toDomain(mockAddressEntity), times(1));
            menuMapperMockedStatic.verify(() -> MenuMapper.toDomain(mockMenuEntity), times(1));
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null RestaurantRequestDto to RestaurantRequest")
    void shouldThrowExceptionWhenMappingNullRestaurantRequestDtoToRestaurantRequest() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RestaurantMapper.toRequest(null));
        assertEquals("RestaurantRequestDto must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Should correctly map RestaurantResponse to RestaurantResponseDto")
    void shouldMapRestaurantResponseToRestaurantResponseDto() {
        try (MockedStatic<UserMapper> userMapperMockedStatic = mockStatic(UserMapper.class);
             MockedStatic<AddressMapper> addressMapperMockedStatic = mockStatic(AddressMapper.class);
             MockedStatic<MenuMapper> menuMapperMockedStatic = mockStatic(MenuMapper.class)) {

            // Given
            List<Menu> menus = Collections.singletonList(mockMenu);
            RestaurantResponse restaurantResponse = new RestaurantResponse(restaurantId, restaurantName, cuisineType, openTime, closeTime, mockUser, mockAddress, menus);

            // When
            when(UserMapper.toEntity(mockUser)).thenReturn(mockUserEntity);
            when(UserMapper.toDto(mockUser)).thenReturn(mockUserResponseDto);
            when(AddressMapper.toEntity(mockAddress)).thenReturn(mockAddressEntity);
            when(MenuMapper.toEntity(mockMenu)).thenReturn(mockMenuEntity);
            when(MenuMapper.fromDomainToDto(mockMenu)).thenReturn(mockMenuResponseDto);
            RestaurantResponseDto restaurantResponseDto = RestaurantMapper.toDto(restaurantResponse);

            // Then
            assertNotNull(restaurantResponseDto);
            assertEquals(restaurantId, restaurantResponseDto.id());
            assertEquals(restaurantName, restaurantResponseDto.name());
            assertEquals(cuisineTypeEnum, restaurantResponseDto.cuisineType());
            assertEquals(mockUserResponseDto, restaurantResponseDto.owner());
            assertEquals(mockAddressEntity, restaurantResponseDto.address());
            assertNotNull(restaurantResponseDto.menus());
            assertEquals(1, restaurantResponseDto.menus().size());

            userMapperMockedStatic.verify(() -> UserMapper.toDto(mockUser), times(1));
            addressMapperMockedStatic.verify(() -> AddressMapper.toEntity(mockAddress), times(1));
            menuMapperMockedStatic.verify(() -> MenuMapper.fromDomainToDto(mockMenu), times(1));
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when mapping null RestaurantResponse to RestaurantResponseDto")
    void shouldThrowExceptionWhenMappingNullRestaurantResponseToRestaurantResponseDto() {
        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RestaurantMapper.toDto(null));
        assertEquals("RestaurantRequest must be not null", exception.getMessage());
    }

    @Test
    @DisplayName("Deve mapear RestaurantEntitu para Restauran com apenas o id e nome")
    void shouldMapRestaurantEntityToRestaurantWithIdAndName() {
        // Given
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName);

        // When
        Restaurant restaurant = RestaurantMapper.toDomain(restaurantEntity);

        // Then
        assertNotNull(restaurant);
        assertEquals(restaurantId, restaurant.getId());
        assertEquals(restaurantName, restaurant.getName());
        assertNull(restaurant.getCuisineType());
        assertNull(restaurant.getOwner());
        assertNull(restaurant.getAddress());
        assertTrue(restaurant.getMenus().isEmpty());
    }

    @Test
    @DisplayName("Deve mapear Restaurant para RestaurantEntity com apenas o id e nome")
    void shouldMapRestaurantToRestaurantEntityWithIdAndName() {
        // Given
        Restaurant restaurant = new Restaurant(restaurantId, restaurantName);

        // When
        RestaurantEntity restaurantEntity = RestaurantMapper.toEntity(restaurant);

        // Then
        assertNotNull(restaurantEntity);
        assertEquals(restaurantId, restaurantEntity.getId());
        assertEquals(restaurantName, restaurantEntity.getName());
        assertNull(restaurantEntity.getCuisineType());
        assertNull(restaurantEntity.getOwner());
        assertNull(restaurantEntity.getAddress());
    }
}


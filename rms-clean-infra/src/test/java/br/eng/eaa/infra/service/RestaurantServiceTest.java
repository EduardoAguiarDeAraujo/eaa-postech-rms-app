package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.RestaurantController;
import br.eng.eaa.application.model.request.RestaurantRequest;
import br.eng.eaa.application.model.response.RestaurantResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;
import br.eng.eaa.infra.api.exception.RestaurantNotFoundException;
import br.eng.eaa.infra.db.dto.request.RestaurantRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.db.dto.response.RestaurantResponseDto;
import br.eng.eaa.infra.db.dto.response.UserResponseDto;
import br.eng.eaa.infra.db.entity.AddressEntity;
import br.eng.eaa.infra.db.entity.CuisineTypeEnum;
import br.eng.eaa.infra.db.entity.MenuEntity;
import br.eng.eaa.infra.db.entity.UserEntity;
import br.eng.eaa.infra.db.mapper.RestaurantMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantServiceTest {

    private RestaurantController restaurantController;
    private RestaurantService restaurantService;
    private MockedStatic<RestaurantMapper> mockRestaurantMapper;

    private UUID restaurantId;
    private String restaurantName;

    private CuisineType cuisineTypeDomain;
    private CuisineTypeEnum cuisineTypeEntity;

    private Menu mockMenu;
    private MenuEntity mockMenuEntity;
    private MenuResponseDto mockMenuResponseDto;

    private Address mockAddressDomain;
    private AddressEntity mockAddressEntity;

    private User mockOwnerDomain;
    private UserEntity mockOwnerEntity;
    private UserResponseDto mockOwnerResponseDto;

    private LocalTime openTime;
    private LocalTime closeTime;

    @BeforeEach
    void setUp() {
        restaurantController = Mockito.mock(RestaurantController.class);
        restaurantService = new RestaurantService(restaurantController);

        mockRestaurantMapper = Mockito.mockStatic(RestaurantMapper.class);

        mockMenu = Mockito.mock(Menu.class);
        mockMenuEntity = Mockito.mock(MenuEntity.class);
        mockMenuResponseDto = Mockito.mock(MenuResponseDto.class);
        mockAddressDomain = Mockito.mock(Address.class);
        mockAddressEntity = Mockito.mock(AddressEntity.class);
        mockOwnerDomain = Mockito.mock(User.class);
        mockOwnerEntity = Mockito.mock(UserEntity.class);
        mockOwnerResponseDto = Mockito.mock(UserResponseDto.class);

        restaurantId = UUID.randomUUID();
        restaurantName = "Restaurante de Maria";
        cuisineTypeDomain = CuisineType.ITALIAN;
        cuisineTypeEntity = CuisineTypeEnum.ITALIAN;

        this.openTime = LocalTime.of(8, 0);
        this.closeTime = LocalTime.of(18, 0);
    }

    @AfterEach
    void tearDown() {
        mockRestaurantMapper.close();
        restaurantController = null;
        restaurantService = null;
        mockMenu = null;
        mockMenuEntity = null;
        mockAddressDomain = null;
        mockAddressEntity = null;
        mockOwnerDomain = null;
        mockOwnerResponseDto = null;
        mockOwnerEntity = null;
    }

    @Test
    @DisplayName("Deve criar um restaurante com sucesso")
    void shouldCreateRestaurantSuccessfully() {
        // Given
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity, mockMenuEntity));
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto(restaurantId, restaurantName, cuisineTypeEntity,openTime, closeTime,  mockOwnerResponseDto, mockAddressEntity, List.of(mockMenuResponseDto, mockMenuResponseDto));
        RestaurantRequest restaurantRequest = new RestaurantRequest(restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu, mockMenu));
        RestaurantResponse restaurantResponse = new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu, mockMenu));

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toRequest(restaurantRequestDto)).thenReturn(restaurantRequest);
        mockRestaurantMapper.when(() -> RestaurantMapper.toDto(restaurantResponse)).thenReturn(restaurantResponseDto);
        when(restaurantController.save(restaurantRequest)).thenReturn(restaurantResponse);
        RestaurantResponseDto savedRestaurant = restaurantService.save(restaurantRequestDto);

        // Then
        assertNotNull(savedRestaurant);
        assertEquals(restaurantId, savedRestaurant.id());
        assertEquals(restaurantName, savedRestaurant.name());
        assertEquals(cuisineTypeEntity, savedRestaurant.cuisineType());
        assertEquals(mockOwnerResponseDto, savedRestaurant.owner());
        assertEquals(mockAddressEntity, savedRestaurant.address());
        assertEquals(List.of(mockMenuResponseDto, mockMenuResponseDto), savedRestaurant.menus());

    }

    @Test
    @DisplayName("Deve buscar um restaurante por ID com sucesso")
    void shouldFindRestaurantByIdSuccessfully() {
        // Given
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerResponseDto, mockAddressEntity, List.of(mockMenuResponseDto, mockMenuResponseDto));
        RestaurantResponse restaurantResponse = new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toDto(restaurantResponse)).thenReturn(restaurantResponseDto);
        when(restaurantController.findById(restaurantId)).thenReturn(restaurantResponse);
        RestaurantResponseDto foundRestaurant = restaurantService.findById(restaurantId);

        // Then
        assertNotNull(foundRestaurant);
        assertEquals(restaurantId, foundRestaurant.id());
        assertEquals(restaurantName, foundRestaurant.name());
        assertEquals(cuisineTypeEntity, foundRestaurant.cuisineType());
    }

    @Test
    @DisplayName("Deve atualizar um restaurante com sucesso")
    void shouldUpdateRestaurantSuccessfully() {
        // Given
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerEntity, mockAddressEntity, List.of(mockMenuEntity, mockMenuEntity));
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerResponseDto, mockAddressEntity, List.of(mockMenuResponseDto, mockMenuResponseDto));
        RestaurantRequest restaurantRequest = new RestaurantRequest(restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));
        RestaurantResponse restaurantResponse = new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toRequest(restaurantRequestDto)).thenReturn(restaurantRequest);
        mockRestaurantMapper.when(() -> RestaurantMapper.toDto(restaurantResponse)).thenReturn(restaurantResponseDto);
        when(restaurantController.update(restaurantRequest)).thenReturn(restaurantResponse);
        RestaurantResponseDto updatedRestaurant = restaurantService.update(restaurantRequestDto);

        // Then
        assertNotNull(updatedRestaurant);
        assertEquals(restaurantId, updatedRestaurant.id());
        assertEquals(restaurantName, updatedRestaurant.name());
        assertEquals(cuisineTypeEntity, updatedRestaurant.cuisineType());
    }

    @Test
    @DisplayName("Deve buscar todos os restaurantes com sucesso")
    void shouldFindAllRestaurantsSuccessfully() {
        // Given
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerResponseDto, mockAddressEntity, List.of(mockMenuResponseDto, mockMenuResponseDto));
        RestaurantResponse restaurantResponse = new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));
        List<RestaurantResponse> restaurantResponses = List.of(
                new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu)),
                new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu))
        );

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toDto(restaurantResponse)).thenReturn(restaurantResponseDto);
        when(restaurantController.findAll()).thenReturn(restaurantResponses);
        List<RestaurantResponseDto> allRestaurants = restaurantService.findAll();

        // Then
        assertNotNull(allRestaurants);
        assertEquals(2, allRestaurants.size());
    }

    @Test
    @DisplayName("Deve deletar um restaurante com sucesso")
    void shouldDeleteRestaurantSuccessfully() {
        // Given
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto(restaurantId, restaurantName, cuisineTypeEntity, openTime, closeTime, mockOwnerResponseDto, mockAddressEntity, List.of(mockMenuResponseDto, mockMenuResponseDto));
        RestaurantResponse restaurantResponse = new RestaurantResponse(restaurantId, restaurantName, cuisineTypeDomain, openTime, closeTime, mockOwnerDomain, mockAddressDomain, List.of(mockMenu));

        // When
        mockRestaurantMapper.when(() -> RestaurantMapper.toDto(restaurantResponse)).thenReturn(restaurantResponseDto);
        when(restaurantController.delete(restaurantId)).thenReturn(true);
        Boolean isDeleted = restaurantService.delete(restaurantId);

        // Then
        assertNotNull(restaurantResponse);
        assertTrue(isDeleted);
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao tentar deletar um restaurante inexistente")
    void shouldThrowExceptionWhenDeletingNonExistentRestaurant() {
        // Given
        UUID nonExistentRestaurantId = UUID.randomUUID();

        // When
        when(restaurantController.delete(nonExistentRestaurantId)).thenReturn(false);
        Boolean isDeleted = restaurantService.delete(nonExistentRestaurantId);

        // Then
        assertNotNull(isDeleted);
        assertFalse(isDeleted);
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar buscar um restaurante inexistente")
    void shouldThrowExceptionWhenFindingNonExistentRestaurant() {
        // Given
        UUID nonExistentRestaurantId = UUID.randomUUID();

        // When
        when(restaurantController.findById(nonExistentRestaurantId)).thenThrow(new RestaurantNotFoundException("Restaurant not found"));

        // Then
        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.findById(nonExistentRestaurantId));
    }

}
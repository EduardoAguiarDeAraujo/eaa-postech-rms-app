package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.infra.api.exception.MenuNotFoundException;
import br.eng.eaa.infra.api.exception.RestaurantNotFoundException;
import br.eng.eaa.infra.db.entity.MenuEntity;
import br.eng.eaa.infra.db.entity.RestaurantEntity;
import br.eng.eaa.infra.db.mapper.MenuMapper;
import br.eng.eaa.infra.db.mapper.RestaurantMapper;
import br.eng.eaa.infra.db.repository.MenuRepository;
import br.eng.eaa.infra.db.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuGatewayTest {

    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;
    private MockedStatic<MenuMapper> mockMenuMapper;
    private MockedStatic<RestaurantMapper> mockRestaurantMapper;
    private MenuGateway menuGateway;

    private UUID menuId;
    private String menuName;
    private String menuDescription;
    private BigDecimal menuPrice;
    private Boolean menuAvailable;
    private String menuImageUrl;
    private RestaurantEntity mockRestaurantEntity;
    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        menuRepository = Mockito.mock(MenuRepository.class);
        restaurantRepository = Mockito.mock(RestaurantRepository.class);
        mockRestaurantEntity = Mockito.mock(RestaurantEntity.class);
        mockMenuMapper = Mockito.mockStatic(MenuMapper.class);
        mockRestaurantMapper = Mockito.mockStatic(RestaurantMapper.class);
        menuGateway = new MenuGateway(menuRepository, restaurantRepository);

        menuId = UUID.randomUUID();
        menuName = "Pizza Margherita";
        menuDescription = "Deliciosa pizza clássica";
        menuPrice = new BigDecimal("45.00");
        menuAvailable = true;
        menuImageUrl = "http://example.com/pizza.jpg";
        restaurantId = UUID.randomUUID();
    }

    @AfterEach
    void tearDown() {
        mockMenuMapper.close();
        menuRepository = null;
        mockRestaurantMapper.close();
        mockRestaurantEntity = null;
        menuGateway = null;
    }

    @Test
    @DisplayName("Deve criar um novo menu com sucesso")
    void shouldCreateMenuSuccessfully() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, mockRestaurantEntity);

        // When
        mockMenuMapper.when(() -> MenuMapper.toEntity(menuDomain)).thenReturn(menuEntity);
        mockMenuMapper.when(() -> MenuMapper.toDomain(menuEntity)).thenReturn(menuDomain);
        when(restaurantRepository.findById(menuDomain.getRestaurantId())).thenReturn(Optional.of(mockRestaurantEntity));
        when(menuRepository.save(menuEntity)).thenReturn(menuEntity);
        Menu savedMenuDomain = menuGateway.save(menuDomain);

        // Then
        verify(menuRepository, times(1)).save(menuEntity);
        assertEquals(menuId, savedMenuDomain.getId());
        assertEquals(menuName, savedMenuDomain.getName());
        assertEquals(menuDescription, savedMenuDomain.getDescription());
        assertEquals(menuPrice, savedMenuDomain.getPrice());
        assertEquals(menuAvailable, savedMenuDomain.getAvailable());
        assertEquals(menuImageUrl, savedMenuDomain.getImageUrl());
        assertEquals(restaurantId, savedMenuDomain.getRestaurantId());
    }

    @Test
    @DisplayName("Deve atualizar um menu com sucesso")
    void shouldUpdateMenuSuccessfully() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, mockRestaurantEntity);

        // When
        mockMenuMapper.when(() -> MenuMapper.toEntity(menuDomain)).thenReturn(menuEntity);
        mockMenuMapper.when(() -> MenuMapper.toDomain(menuEntity)).thenReturn(menuDomain);
        when(menuRepository.findById(menuDomain.getId())).thenReturn(Optional.of(menuEntity));
        when(restaurantRepository.findById(menuDomain.getRestaurantId())).thenReturn(Optional.of(mockRestaurantEntity));
        when(menuRepository.save(menuEntity)).thenReturn(menuEntity);
        Menu updatedMenuDomain = menuGateway.update(menuDomain);

        // Then
        verify(menuRepository, times(1)).save(menuEntity);
        assertEquals(menuId, updatedMenuDomain.getId());
        assertEquals(menuName, updatedMenuDomain.getName());
        assertEquals(menuDescription, updatedMenuDomain.getDescription());
        assertEquals(menuPrice, updatedMenuDomain.getPrice());
        assertEquals(menuAvailable, updatedMenuDomain.getAvailable());
        assertEquals(menuImageUrl, updatedMenuDomain.getImageUrl());
        assertEquals(restaurantId, updatedMenuDomain.getRestaurantId());
    }

    @Test
    @DisplayName("Deve buscar um menu por ID com sucesso")
    void shouldFindMenuByIdSuccessfully() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, mockRestaurantEntity);

        // When
        mockMenuMapper.when(() -> MenuMapper.toDomain(menuEntity)).thenReturn(menuDomain);
        when(menuRepository.findById(menuId)).thenReturn(java.util.Optional.of(menuEntity));
        Menu foundMenuDomain = menuGateway.findById(menuId);

        // Then
        verify(menuRepository, times(1)).findById(menuId);
        assertEquals(menuId, foundMenuDomain.getId());
        assertEquals(menuName, foundMenuDomain.getName());
        assertEquals(menuDescription, foundMenuDomain.getDescription());
        assertEquals(menuPrice, foundMenuDomain.getPrice());
        assertEquals(menuAvailable, foundMenuDomain.getAvailable());
        assertEquals(menuImageUrl, foundMenuDomain.getImageUrl());
        assertEquals(restaurantId, foundMenuDomain.getRestaurantId());
    }

    @Test
    @DisplayName("Deve excluir um menu por ID com sucesso")
    void shouldDeleteMenuByIdSuccessfully() {
        // Given
        MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, mockRestaurantEntity);

        // When
        when(menuRepository.findById(menuId)).thenReturn(Optional.of(menuEntity));
        doNothing().when(menuRepository).delete(menuEntity);
        Boolean isDeleted = menuGateway.delete(menuId);

        // Then
        verify(menuRepository, times(1)).delete(menuEntity);
        assertEquals(true, isDeleted);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar excluir um menu inexistente")
    void shouldReturnFalseWhenDeletingNonExistentMenu() {
        // Given
        UUID nonExistentMenuId = UUID.randomUUID();

        // When
        when(menuRepository.findById(nonExistentMenuId)).thenReturn(Optional.empty());
        MenuNotFoundException menuNotFoundException = assertThrows(MenuNotFoundException.class, () -> {menuGateway.delete(nonExistentMenuId);}) ;
        // Then
        verify(menuRepository, times(1)).findById(nonExistentMenuId);
        assertEquals("Menu not found with id: " + nonExistentMenuId, menuNotFoundException.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma lista de menus com sucesso")
    void shouldReturnListOfMenusSuccessfully() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, mockRestaurantEntity);

        // When
        mockMenuMapper.when(() -> MenuMapper.toDomain(menuEntity)).thenReturn(menuDomain);
        when(menuRepository.findAll()).thenReturn(List.of(menuEntity));
        List<Menu> menus = menuGateway.findAll();

        // Then
        verify(menuRepository, times(1)).findAll();
        assertEquals(1, menus.size());
        assertEquals(menuId, menus.get(0).getId());
        assertEquals(menuName, menus.get(0).getName());
        assertEquals(menuDescription, menus.get(0).getDescription());
        assertEquals(menuPrice, menus.get(0).getPrice());
        assertEquals(menuAvailable, menus.get(0).getAvailable());
        assertEquals(menuImageUrl, menus.get(0).getImageUrl());
        assertEquals(restaurantId, menus.get(0).getRestaurantId());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia de menus quando não houver nenhum")
    void shouldReturnEmptyListOfMenusWhenNoneExist() {
        // Given

        // When
        when(menuRepository.findAll()).thenReturn(List.of());
        List<Menu> menus = menuGateway.findAll();

        // Then
        verify(menuRepository, times(1)).findAll();
        assertTrue(menus.isEmpty(), "Expected an empty list of menus");
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar buscar um menu inexistente por ID")
    void shouldThrowExceptionWhenMenuNotFoundById() {
        // Given
        UUID nonExistentMenuId = UUID.randomUUID();

        // When
        when(menuRepository.findById(nonExistentMenuId)).thenReturn(Optional.empty());
        MenuNotFoundException menuNotFoundException = assertThrows(MenuNotFoundException.class, () -> {menuGateway.findById(nonExistentMenuId);});
        // Then
        verify(menuRepository, times(1)).findById(nonExistentMenuId);
        assertEquals("Menu not found with id: " + nonExistentMenuId, menuNotFoundException.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar atualizar menu inexistente")
    void shouldThrowExceptionWhenUpdatingNonExistentMenu() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        when(menuRepository.findById(menuId)).thenReturn(Optional.empty());
        MenuNotFoundException menuNotFoundException = assertThrows(MenuNotFoundException.class, () -> {menuGateway.update(menuDomain);});

        // Then
        verify(menuRepository, times(1)).findById(menuId);
        assertEquals("Menu not found with id: " + menuId, menuNotFoundException.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção RestaurantNotFoundException quando tentar salvar menu com id do restaurante inválido")
    void shouldThrowExceptionWhenSavingMenuWithInvalidRestaurantId() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        when(restaurantRepository.findById(menuDomain.getRestaurantId())).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {menuGateway.save(menuDomain);});

        // Then
        verify(restaurantRepository, times(1)).findById(menuDomain.getRestaurantId());
        assertEquals("Restaurant not found with id: " + menuDomain.getRestaurantId(), exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção RestaurantNotFoundException quando tentar atualizar menu com id do restaurante inválido")
    void shouldThrowExceptionWhenUpdatingMenuWithInvalidRestaurantId() {
        // Given
        Menu menuDomain = new Menu(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuEntity menuEntity = new MenuEntity(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, mockRestaurantEntity);

        // When
        when(menuRepository.findById(menuDomain.getId())).thenReturn(Optional.of(menuEntity));
        when(restaurantRepository.findById(menuDomain.getRestaurantId())).thenReturn(Optional.empty());
        RestaurantNotFoundException exception = assertThrows(RestaurantNotFoundException.class, () -> {menuGateway.update(menuDomain);});

        // Then
        verify(restaurantRepository, times(1)).findById(menuDomain.getRestaurantId());
        assertEquals("Restaurant not found with id: " + menuDomain.getRestaurantId(), exception.getMessage());
    }


}
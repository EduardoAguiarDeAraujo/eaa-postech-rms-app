package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.MenuController;
import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.infra.db.dto.request.MenuRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.db.mapper.MenuMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MenuServiceTest {

    private MenuController menuController;
    private MockedStatic<MenuMapper> mockMenuMapper;
    private MenuService menuService;

    private UUID menuId;
    private String menuName;
    private String menuDescription;
    private BigDecimal menuPrice;
    private Boolean menuAvailable;
    private String menuImageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        menuController = Mockito.mock(MenuController.class);
        mockMenuMapper = Mockito.mockStatic(MenuMapper.class);
        menuService = new MenuService(menuController);

        menuId = UUID.randomUUID();
        menuName = "Pizza Margherita";
        menuDescription = "Delicious classic pizza";
        menuPrice = new BigDecimal("45.00");
        menuAvailable = true;
        menuImageUrl = "http://example.com/pizza.jpg";
        restaurantId = UUID.randomUUID();
    }

    @AfterEach
    void tearDown() {
        mockMenuMapper.close();
        menuController = null;
    }

    @Test
    @DisplayName("Deve criar um menu com sucesso")
    void shouldCreateMenuSuccessfully() {
        // Given
        MenuRequestDto menuRequestDto = new MenuRequestDto(null, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuResponseDto menuResponseDto = new MenuResponseDto(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl,restaurantId);

        MenuRequest menuRequest = new MenuRequest(menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuResponse menuResponse = new MenuResponse(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        mockMenuMapper.when(() -> MenuMapper.toRequest(menuRequestDto)).thenReturn(menuRequest);
        mockMenuMapper.when(() -> MenuMapper.toDto(menuResponse)).thenReturn(menuResponseDto);
        when(menuController.save(menuRequest)).thenReturn(menuResponse);
        MenuResponseDto expectedMenuResponseDto = menuService.save(menuRequestDto);

        // Then
        assertNotNull(expectedMenuResponseDto);
        assertEquals(menuId, expectedMenuResponseDto.id());
        assertEquals(menuName, expectedMenuResponseDto.name());
        assertEquals(menuDescription, expectedMenuResponseDto.description());
        assertEquals(menuPrice, expectedMenuResponseDto.price());
        assertEquals(menuAvailable, expectedMenuResponseDto.available());
        assertEquals(menuImageUrl, expectedMenuResponseDto.imageUrl());
        assertEquals(restaurantId, expectedMenuResponseDto.restaurantId());
    }

    @Test
    @DisplayName("Deve buscar um menu por ID com sucesso")
    void shouldFindMenuByIdSuccessfully() {
        // Given
        MenuResponse menuResponse = new MenuResponse(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuResponseDto menuResponseDto = new MenuResponseDto(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        mockMenuMapper.when(() -> MenuMapper.toDto(menuResponse)).thenReturn(menuResponseDto);
        when(menuController.findById(menuId)).thenReturn(menuResponse);
        MenuResponseDto foundMenu = menuService.findById(menuId);

        // Then
        assertNotNull(foundMenu);
        assertEquals(menuId, foundMenu.id());
        assertEquals(menuName, foundMenu.name());
        assertEquals(menuDescription, foundMenu.description());
        assertEquals(menuPrice, foundMenu.price());
        assertEquals(menuAvailable, foundMenu.available());
        assertEquals(menuImageUrl, foundMenu.imageUrl());
        assertEquals(restaurantId, foundMenu.restaurantId());
    }

    @Test
    @DisplayName("Deve atualizar um menu com sucesso")
    void shouldUpdateMenuSuccessfully() {
        // Given
        MenuRequestDto menuRequestDto = new MenuRequestDto(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuRequest menuRequest = new MenuRequest(menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuResponse menuResponse = new MenuResponse(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuResponseDto menuResponseDto = new MenuResponseDto(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        mockMenuMapper.when(() -> MenuMapper.toRequest(menuRequestDto)).thenReturn(menuRequest);
        mockMenuMapper.when(() -> MenuMapper.toDto(menuResponse)).thenReturn(menuResponseDto);
        when(menuController.update(menuRequest)).thenReturn(menuResponse);
        MenuResponseDto updatedMenu = menuService.update(menuRequestDto);

        // Then
        assertNotNull(updatedMenu);
        assertEquals(menuId, updatedMenu.id());
        assertEquals(menuName, updatedMenu.name());
        assertEquals(menuDescription, updatedMenu.description());
        assertEquals(menuPrice, updatedMenu.price());
        assertEquals(menuAvailable, updatedMenu.available());
        assertEquals(menuImageUrl, updatedMenu.imageUrl());
        assertEquals(restaurantId, updatedMenu.restaurantId());
    }

    @Test
    @DisplayName("Deve excluir um menu com sucesso")
    void shouldDeleteMenuSuccessfully() {
        // Given
        MenuResponse menuResponse = new MenuResponse(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        when(menuController.delete(menuId)).thenReturn(true);
        Boolean deletedMenu = menuService.delete(menuId);

        // Then
        assertNotNull(deletedMenu);
        assertTrue(deletedMenu);
    }

    @Test
    @DisplayName("Deve retornar false ao tentar excluir um menu inexistente")
    void shouldReturnFalseWhenDeletingNonExistentMenu() {
        // Given
        UUID nonExistentMenuId = UUID.randomUUID();

        // When
        when(menuController.delete(nonExistentMenuId)).thenReturn(false);
        Boolean deletedMenu = menuService.delete(nonExistentMenuId);

        // Then
        assertNotNull(deletedMenu);
        assertFalse(deletedMenu);
    }

    @Test
    @DisplayName("Deve buscar todos os menus com sucesso")
    void shouldFindAllMenusSuccessfully() {
        // Given
        MenuResponse menuResponse = new MenuResponse(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);
        MenuResponseDto menuResponseDto = new MenuResponseDto(menuId, menuName, menuDescription, menuPrice, menuAvailable, menuImageUrl, restaurantId);

        // When
        mockMenuMapper.when(() -> MenuMapper.toDto(menuResponse)).thenReturn(menuResponseDto);
        when(menuController.findAll()).thenReturn(List.of(menuResponse));
        List<MenuResponseDto> allMenus = menuService.findAll();

        // Then
        assertNotNull(allMenus);
        assertFalse(allMenus.isEmpty());
        assertEquals(1, allMenus.size());
        assertEquals(menuId, allMenus.get(0).id());
        assertEquals(menuName, allMenus.get(0).name());
        assertEquals(menuDescription, allMenus.get(0).description());
        assertEquals(menuPrice, allMenus.get(0).price());
        assertEquals(menuAvailable, allMenus.get(0).available());
        assertEquals(menuImageUrl, allMenus.get(0).imageUrl());
        assertEquals(restaurantId, allMenus.get(0).restaurantId());
    }

}
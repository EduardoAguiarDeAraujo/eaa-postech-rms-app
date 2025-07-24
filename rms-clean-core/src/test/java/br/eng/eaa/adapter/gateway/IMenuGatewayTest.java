package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IMenuGatewayTest {

    private IMenuGateway menuGateway;
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        menuGateway = Mockito.mock(IMenuGateway.class);
        this.id = UUID.randomUUID();
        this.name = "Contra-filé acebolado";
        this.description = "Contra-filé acebolado com arroz branco e feijão";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve retornar um menu salvo")
    void givenAValidMenu_whenCallsSave_thenReturnSavedMenu() {
        // GIVEN
        Menu aMenu = new Menu(id,name,description,price,available,imageUrl, restaurantId);
        Menu savedMenu = new Menu(id,name,description,price,available,imageUrl, restaurantId);

        // WHEN
        when(menuGateway.save(aMenu)).thenReturn(savedMenu);
        Menu actualMenu = menuGateway.save(aMenu);

        // THEN
        verify(menuGateway, times(1)).save(aMenu);
        assertEquals(savedMenu, actualMenu);
    }

    @Test
    @DisplayName("Deve atualizar um menu")
    void givenAValidMenu_whenCallsUpdate_thenReturnUpdatedMenu() {
        // GIVEN
        Menu updatedMenuData = new Menu(id,name,description,price,available,imageUrl, restaurantId);

        // WHEN
        when(menuGateway.update(updatedMenuData)).thenReturn(updatedMenuData);
        Menu actualMenu = menuGateway.update(updatedMenuData);

        // THEN
        verify(menuGateway, times(1)).update(updatedMenuData);
        assertEquals(updatedMenuData, actualMenu);
    }

    @Test
    @DisplayName("Deve retornar um menu para um id válido")
    void givenAValidId_whenCallsFindById_thenReturnMenu() {
        // GIVEN
        Menu expectedMenu = new Menu(id,name,description,price,available,imageUrl, restaurantId);

        // WHEN
        when(menuGateway.findById(id)).thenReturn(expectedMenu);
        Menu actualMenu = menuGateway.findById(id);

        // THEN
        verify(menuGateway, times(1)).findById(id);
        assertEquals(expectedMenu, actualMenu);
    }

    @Test
    @DisplayName("Deve retornar null para um id invalido")
    void givenAnInvalidId_whenCallsFindById_thenReturnNull() {
        // GIVEN
        UUID invalidId = UUID.randomUUID();

        // WHEN
        when(menuGateway.findById(invalidId)).thenReturn(null);
        Menu actualMenu = menuGateway.findById(invalidId);

        // THEN
        verify(menuGateway, times(1)).findById(invalidId);
        assertNull(actualMenu);
    }

    @Test
    @DisplayName("Deve retornar uma lista de menus")
    void whenCallsFindAll_thenReturnAllMenus() {
        // GIVEN
        List<Menu> expectedMenus = Arrays.asList(
                new Menu(UUID.randomUUID(),name,description,price,available,imageUrl, restaurantId),
                new Menu(UUID.randomUUID(),name,description,price,available,imageUrl, restaurantId)
        );

        // WHEN
        when(menuGateway.findAll()).thenReturn(expectedMenus);
        List<Menu> actualMenus = menuGateway.findAll();

        // THEN
        verify(menuGateway, times(1)).findAll();
        assertEquals(expectedMenus.size(), actualMenus.size());
        assertTrue(actualMenus.containsAll(expectedMenus));
    }

    @Test
    @DisplayName("Deve validar uma exclusão true")
    void givenAValidId_whenCallsDelete_thenReturnTrue() {
        // GIVEN
        UUID idToDelete = UUID.randomUUID();

        // WHEN
        when(menuGateway.delete(idToDelete)).thenReturn(true);

        Boolean actualResult = menuGateway.delete(idToDelete);

        // THEN
        verify(menuGateway, times(1)).delete(idToDelete);
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Deve validar uma exclusão false")
    void givenAnInvalidId_whenCallsDelete_thenReturnFalse() {
        // GIVEN
        UUID invalidId = UUID.randomUUID();

        // WHEN
        when(menuGateway.delete(invalidId)).thenReturn(false);

        Boolean actualResult = menuGateway.delete(invalidId);

        // THEN
        verify(menuGateway, times(1)).delete(invalidId);
        assertFalse(actualResult);
    }


}
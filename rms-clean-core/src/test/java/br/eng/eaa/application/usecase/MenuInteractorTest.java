package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IMenuGateway;
import br.eng.eaa.application.boundary.output.MenuOutputPort;
import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class MenuInteractorTest {

    private MenuInteractor menuInteractor;
    private IMenuGateway menuGateway;
    private MenuOutputPort menuOutputPort;
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        this.menuGateway = Mockito.mock(IMenuGateway.class);
        this.menuOutputPort = Mockito.mock(MenuOutputPort.class);
        this.menuInteractor = new MenuInteractor(menuGateway, menuOutputPort);
        this.id = UUID.randomUUID();
        this.name = "Contra-filé com fritas";
        this.description = "Contra-filé com fritas, arroz branco e feijão carioquinha";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve salvar um menu válido")
    void givenAValidMenu_whenCallsSave_thenReturnSavedMenu() {
        //GIVEN
        MenuRequest menuRequest = new  MenuRequest(name, description,price,available,imageUrl,restaurantId);
        MenuResponse menuResponse = new MenuResponse(id, name, description, price, available, imageUrl, restaurantId);

        //WHEN
        when(menuInteractor.save(menuRequest)).thenReturn(menuResponse);
        MenuResponse actualMenu = menuInteractor.save(menuRequest);

        //THEN
        assertNotNull(actualMenu);
        assertEquals(menuResponse, actualMenu);
    }

    @Test
    @DisplayName("Deve atualizar um menu válido")
    void givenAValidMenu_whenCallsUpdate_thenReturnUpdatedMenu() {
        //GIVEN
        MenuRequest menuRequest = new  MenuRequest(id, name, description,price,available,imageUrl,restaurantId);
        MenuResponse menuResponse = new MenuResponse(id, name, description, price, available, imageUrl, restaurantId);

        //WHEN
        when(menuInteractor.update(menuRequest)).thenReturn(menuResponse);
        MenuResponse actualMenu = menuInteractor.update(menuRequest);

        //THEN
        assertEquals(menuResponse, actualMenu);
    }

    @Test
    @DisplayName("Deve excluir um menu por ID válido")
    void shouldDeletedMenuById() {
        //GIVEN
        Boolean menuResponse = true;
        //WHEN
        when(menuInteractor.delete(id)).thenReturn(menuResponse);
        Boolean actualResponse = menuInteractor.delete(id);
        // THEN
        assertEquals(menuResponse, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um um menu por ID válido")
    void shouldReturnMenuById() {
        //GIVEN
        MenuResponse menuResponse = new MenuResponse(id, name, description, price, available, imageUrl, restaurantId);
        //WHEN
        when(menuInteractor.findById(id)).thenReturn(menuResponse);
        MenuResponse actualResponse = menuInteractor.findById(id);
        // THEN
        assertEquals(menuResponse, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um lista de menus")
    void shouldReturnListAllMenu() {
        //GIVEN
        List<MenuResponse> menuResponses = List.of(
                new MenuResponse(UUID.randomUUID(), name, description, price, available, imageUrl, restaurantId),
                new MenuResponse(UUID.randomUUID(), name, description, price, available, imageUrl, restaurantId)
        );
        //WHEN
        when(menuInteractor.findAll()).thenReturn(menuResponses);
        List<MenuResponse> actualResponses = menuInteractor.findAll();
        // THEN
        assertEquals(menuResponses, actualResponses);
    }


}
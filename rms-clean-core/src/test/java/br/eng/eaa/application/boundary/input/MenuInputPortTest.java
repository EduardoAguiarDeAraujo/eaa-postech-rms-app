package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuInputPortTest {

    private MenuInputPort menuInputPort;
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp() {
        this.menuInputPort = Mockito.mock(MenuInputPort.class);
        this.id = UUID.randomUUID();
        this.name = "Contra-filé acebolado";
        this.description = "Contra-filé acebolado com arroz branco e feijão";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve retornar um MenuResponse salvo")
    void givenAValidMenu_whenCallsSave_thenReturnSavedMenu() {
        // GIVEN
        MenuRequest menuRequest = new MenuRequest(id,name,description,price,available,imageUrl, restaurantId);
        MenuResponse menuResponse = new MenuResponse(id,name,description,price,available,imageUrl, restaurantId);

        // WHEN
        when(menuInputPort.save(menuRequest)).thenReturn(menuResponse);
        MenuResponse actualMenu = menuInputPort.save(menuRequest);

        // THEN
        verify(menuInputPort, times(1)).save(menuRequest);
        assertEquals(menuResponse, actualMenu);
    }

}
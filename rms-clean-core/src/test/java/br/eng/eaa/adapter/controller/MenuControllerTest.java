package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IMenuGateway;
import br.eng.eaa.adapter.gateway.MenuGatewayStub;
import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuControllerTest {

    private IMenuGateway menuGateway;
    private MenuController menuController;
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp(){
        this.menuGateway = new MenuGatewayStub();
        this.menuController = new MenuController(menuGateway);
        this.id = UUID.fromString("31d21f36-6edc-4f3d-9fda-879e09c739fe");
        this.name = "Contra-filé acebolado";
        this.description = "Contra-filé acebolado com arroz branco e feijão";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve salvar um usuário válido")
    void givenAValidMenu_whenCallsSave_thenReturnSavedMenu() {
        //GIVEN
        MenuRequest menuRequest = new MenuRequest(name, description,price,available,imageUrl, restaurantId);
        MenuResponse menuResponse = new MenuResponse(id, name, description, price, available, imageUrl, restaurantId);
        //WHEN
        MenuResponse actualResponse = menuController.save(menuRequest);
        //THEN
        assertEquals(menuResponse.getId(), actualResponse.getId());
    }

    @Test
    @DisplayName("Deve atualizar um usuário válido")
    void givenAValidMenuRequest_whenCallsUpdate_thenReturnUpdatedMenuResponse() {
        //GIVEN
        MenuRequest menuRequest = new MenuRequest(id, name, description,price,available,imageUrl, restaurantId);
        MenuResponse menuResponse = new MenuResponse(id, name, description, price, available, imageUrl, restaurantId);
        //WHEN
        MenuResponse actualResponse = menuController.update(menuRequest);
        //THEN
        assertEquals(menuResponse.getId(), actualResponse.getId());
    }

    @Test
    @DisplayName("Deve retornar true na exclusão de um usuário por id válido")
    void givenAValidMenuRequest_whenCallsDelete_thenReturnDeletedMenuResponse() {
        //GIVEN
        Boolean isDeleted = true;
        UUID id = this.id;
        //WHEN
        Boolean actualResponse = menuController.delete(id);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar false na exclusão de um usuário por id válido")
    void givenAInvalidMenuRequest_whenCallsDelete_thenReturnDeletedMenuResponse() {
        //GIVEN
        Boolean isDeleted = false;
        UUID id = UUID.randomUUID();
        //WHEN
        Boolean actualResponse = menuController.delete(id);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um usuário válido quando pesquisar por id válido")
    void givenAValidMenuRequest_whenCallsFindById_thenReturnAMenuResponse() {
        //GIVEN
        UUID id = this.id;
        MenuResponse menuResponse = new MenuResponse(id, name, description, price, available, imageUrl, restaurantId);

        //WHEN
        MenuResponse actualResponse = menuController.findById(id);

        //THEN
        assertEquals(menuResponse.getId(), actualResponse.getId());
    }

    @Test
    @DisplayName("Deve retornar um usuário válido quando pesquisar por id válido")
    void givenAValidMenuRequest_whenCallsFindAll_thenReturnAListOfMenuResponse() {
        //GIVEN
        List<MenuResponse> menuResponses = List.of(
                new MenuResponse(id, name, description, price, available, imageUrl, restaurantId),
                new MenuResponse(id, name, description, price, available, imageUrl, restaurantId)
        );

        //WHEN
        List<MenuResponse> actualResponses = menuController.findAll();

        //THEN
        assertEquals(menuResponses.size(), actualResponses.size());
    }


}
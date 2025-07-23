package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuPresenterTest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    @BeforeEach
    void setUp(){
        this.id = UUID.randomUUID();
        this.name = "Contra-filé acebolado";
        this.description = "Contra-filé acebolado com arroz branco e feijão";
        this.price = BigDecimal.valueOf(45.00);
        this.available = true;
        this.imageUrl = "https://s3-sa-east-1.amazonaws.com/deliveryon-uploads/products/debemcomavidasushi/76_637d7df0b1099.jpg";
        this.restaurantId = UUID.randomUUID();
    }


    @Test
    @DisplayName("Deve retornar um menu válido")
    void shouldReturnValidMenu(){
        //GIVEN
        Menu menu = new Menu(id,name,description,price,available,imageUrl, restaurantId);
        MenuResponse menuResponse = new MenuResponse(id,name,description,price,available,imageUrl, restaurantId);

        //WHEN
        MenuResponse actualResponse = new MenuPresenter().execute(menu);

        //THEN
        assertEquals(menuResponse.getId(), actualResponse.getId());
        assertEquals(menuResponse.getName(), actualResponse.getName());
        assertEquals(menuResponse.getDescription(), actualResponse.getDescription());
        assertEquals(menuResponse.getPrice(), actualResponse.getPrice());
        assertEquals(menuResponse.getAvailable(), actualResponse.getAvailable());
        assertEquals(menuResponse.getImageUrl(), actualResponse.getImageUrl());
        assertEquals(menuResponse.getRestaurant(), actualResponse.getRestaurant());
    }

    @Test
    @DisplayName("Deve retornar uma lista de menus válido")
    void shouldReturnListAllMenu(){
        //GIVEN
        List<Menu> menus = List.of(
                new Menu(id,name,description,price,available,imageUrl, restaurantId),
                new Menu(id,name,description,price,available,imageUrl, restaurantId)
        );
        List<MenuResponse> menuResponses = List.of(
                new MenuResponse(id,name,description,price,available,imageUrl, restaurantId),
                new MenuResponse(id,name,description,price,available,imageUrl, restaurantId)
        );

        //WHEN
        List<MenuResponse>  actualResponses = new MenuPresenter().execute(menus);

        //THEN
        assertEquals(menuResponses.size(), actualResponses.size());
    }

    @Test
    @DisplayName("Deve retornar um boolean = true")
    void shouldReturnTrue(){
        //GIVEN
        Boolean isDeleted = true;
        //WHEN
        Boolean actualResponse = new MenuPresenter().execute(isDeleted);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }


}
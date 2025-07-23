package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.service.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MenuApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MenuService menuService;

    @Test
    @DisplayName("Should create a menu successfully")
    public void shouldCreateMenuSuccessfully() throws Exception {
        // Given
        String menuJson = """
                {
                    "name": "Pizza Margherita",
                    "description": "Clássica pizza com tomate, mussarela e manjericão",
                    "price": 29.99,
                    "available": true,
                    "imageUrl": "http://example.com/pizza.jpg",
                    "restaurantId": "6c642e31-55ca-11f0-a29e-04bf1b4887e6"
                }
                """;
        mockMvc.perform(post("/api/v1/menus/create")
                .contentType("application/json")
                .content(menuJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar um menu por ID")
    public void shouldReturnMenuById() throws Exception {
        // Given
        String menuId = "9d5f1c6b-55ca-11f0-a29e-04bf1b4887e6";
        mockMvc.perform(get("/api/v1/menus/" + menuId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar uma lista de menus")
    public void shouldReturnListOfMenus() throws Exception {
        // When
        mockMvc.perform(get("/api/v1/menus"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve excluir um menu por ID")
    public void shouldDeleteMenuById() throws Exception {
        // Given
        String menuId = "9d5f1c6b-55ca-11f0-a29e-04bf1b4887e6";
        mockMvc.perform(delete("/api/v1/menus/delete/" + menuId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar um menu com sucesso")
    public void shouldUpdateMenuSuccessfully() throws Exception {
        // Given
        String updatedMenuJson = """
                {
                    "id": "9d5f1c6b-55ca-11f0-a29e-04bf1b4887e6",
                    "name": "MARGUERITA DOCE",
                    "description": "Pizza com doce de leita, tomate e manjericão",
                    "price": 69.90,
                    "available": false,
                    "imageUrl": "https://example.com/images/marguerita.jpg",
                    "restaurantId": "6c642e31-55ca-11f0-a29e-04bf1b4887e6"
                }
                """;
        mockMvc.perform(put("/api/v1/menus/update")
                .contentType("application/json")
                .content(updatedMenuJson))
                .andExpect(status().isOk());
    }

}
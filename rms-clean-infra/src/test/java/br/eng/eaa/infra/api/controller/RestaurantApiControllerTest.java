package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.service.RestaurantService;
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
class RestaurantApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestaurantService restaurantService;

    @Test
    @DisplayName("Deve criar um restaurante com sucesso")
    void shouldCreateRestaurantSuccessfully() throws Exception {
        // Given
        String restaurantJson = """
                {
                    "name":"Rebeca Food",
                    "cuisineType":"JAPANESE",
                    "openTime": "11:00:00",
                    "closeTime": "22:00:00",
                    "owner": {
                        "id":"82c9e3fe-5537-11f0-a29e-04bf1b4887e6",
                        "userName":"Eduardo"
                    },
                    "address": {
                        "street": "Av. Paulista, 2207",
                        "city":"Sao Paulo",
                        "state":"SP",
                        "zipCode":"06250-000"
                    },
                    "menus": []
                }
                """;
        mockMvc.perform(post("/api/v1/restaurants/create")
                .contentType("application/json")
                .content(restaurantJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve buscar um restaurante por ID")
    void shouldReturnRestaurantById() throws Exception {
        // Given
        String restaurantId = "6c642e31-55ca-11f0-a29e-04bf1b4887e6";
        mockMvc.perform(get("/api/v1/restaurants/" + restaurantId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar uma lista de restaurantes")
    void shouldReturnListOfRestaurants() throws Exception {
        // When
        mockMvc.perform(get("/api/v1/restaurants"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve excluir um restaurante por ID")
    void shouldDeleteRestaurantById() throws Exception {
        // Given
        String restaurantId = "6c642e31-55ca-11f0-a29e-04bf1b4887e6";
        mockMvc.perform(delete("/api/v1/restaurants/delete/" + restaurantId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar um restaurante com sucesso")
    void shouldUpdateRestaurantSuccessfully() throws Exception {
        // Given
        String updatedRestaurantJson = """
                {
                    "id": "6c642e31-55ca-11f0-a29e-04bf1b4887e6",
                    "name": "Rebeca Food Updated",
                    "cuisineType": "ITALIAN",
                    "openTime": "11:00:00",
                    "closeTime": "22:00:00",
                    "owner": {
                        "id": "82c9e3fe-5537-11f0-a29e-04bf1b4887e6",
                        "userName": "Eduardo"
                    },
                    "address": {
                        "street": "Av. Paulista, 2207",
                        "city": "Sao Paulo",
                        "state": "SP",
                        "zipCode": "06250-000"
                    },
                    "menus": []
                }
                """;
        mockMvc.perform(put("/api/v1/restaurants/update")
                .contentType("application/json")
                .content(updatedRestaurantJson))
                .andExpect(status().isOk());
    }


}
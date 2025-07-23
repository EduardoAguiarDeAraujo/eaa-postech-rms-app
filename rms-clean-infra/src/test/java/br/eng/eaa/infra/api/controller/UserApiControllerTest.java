package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @DisplayName("Should create a user successfully")
    public void shouldCreateUserSuccessfully() throws Exception {
        // Given
        String userJson = """
                {
                    "username": "Eduardo",
                    "password": "MinhaSenhaForte10#",
                    "roles": [
                        {
                            "name": "ADMIN"
                        },
                        {
                            "name": "OWNER"
                        }
                    ]
                }
                """;
        mockMvc.perform(post("/api/v1/users/create")
                .contentType("application/json")
                .content(userJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar um usuãrio por ID")
    public void shouldReturnUserById() throws Exception {
        // Given
        String userId = "e0f279d7-5536-11f0-a29e-04bf1b4887e6"; // Exemplo de ID de usuário
        mockMvc.perform(get("/api/v1/users/" + userId)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários")
    public void shouldReturnListOfUsers() throws Exception {
        // When
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve excluir um usuário por ID")
    public void shouldDeleteUserById() throws Exception {
        // Given
        String userId = "e0f279d7-5536-11f0-a29e-04bf1b4887e6";
        mockMvc.perform(delete("/api/v1/users/delete/" + userId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    public void shouldUpdateUser() throws Exception {
        // Given
        String updatedUserJson = """
                {
                    "id": "e0f279d7-5536-11f0-a29e-04bf1b4887e6",
                    "username": "EduardoUpdated",
                    "password": "MinhaSenhaForte10#",
                    "roles": [
                        {
                            "name": "ADMIN"
                        }
                    ]
                }
                """;
        mockMvc.perform(put("/api/v1/users/update")
                .contentType("application/json")
                .content(updatedUserJson))
                .andExpect(status().isOk());
    }



}
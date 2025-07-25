package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.service.RoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RoleService roleService;

    @Test
    @DisplayName("Deve criar uma role com sucesso")
    void shouldCreateRoleSuccessfully() throws Exception {
        // Given
        String roleJson = """
                {
                    "name": "WAITER"
                }
                """;
        mockMvc.perform(post("/api/v1/roles/create")
                .contentType("application/json")
                .content(roleJson))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar uma role por ID")
    void shouldReturnRoleById() throws Exception {
        // Given
        String roleId = "e0f279d7-5536-11f0-a29e-04bf1b4887e6"; // Exemplo de ID de role
        mockMvc.perform(get("/api/v1/roles/" + roleId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar uma lista de roles")
    void shouldReturnListOfRoles() throws Exception {
        // When
        mockMvc.perform(get("/api/v1/roles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DisplayName("Deve excluir uma role com sucesso")
    void shouldDeleteRoleSuccessfully() throws Exception {
        // Given
        String roleId = "4d5e6f7a-55ca-11f0-a29e-04bf1b4887e6";
        mockMvc.perform(delete("/api/v1/roles/delete/" + roleId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar uma role com sucesso")
    void shouldUpdateRoleSuccessfully() throws Exception {
        // Given
        String updatedRoleJson = """
                {
                    "name": "WAITER"
                }
                """;
        mockMvc.perform(put("/api/v1/roles/update")
                .contentType("application/json")
                .content(updatedRoleJson))
                .andExpect(status().isOk());
    }


}
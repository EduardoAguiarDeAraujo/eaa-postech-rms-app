package br.eng.eaa.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenApiConfigTest {

    private OpenApiConfig openApiConfig;

    @BeforeEach
    void setUp() {
        openApiConfig = new OpenApiConfig();
    }
    @Test
    @DisplayName("Deve retornar um objeto OpenAPI configurado corretamente")
    void customOpenApi_shouldReturnCorrectlyConfiguredOpenAPI() {
        OpenAPI openAPI = openApiConfig.customOpenAPI();

        assertNotNull(openAPI, "OpenAPI object should not be null");

        Info info = openAPI.getInfo();
        assertNotNull(info, "Info object should not be null");
        assertEquals("Restaurant Management Systen API", info.getTitle(), "Title should match");
        assertEquals("1.0.0", info.getVersion(), "Version should match");
        assertEquals("API para gerenciamento de restaurantes", info.getDescription(), "Description should match");
        assertNotNull(info.getLicense(), "License should not be null");
        assertEquals("eaa.eng.br", info.getLicense().getName(), "License name should match");
        assertEquals("http://github.com/eduardoaguiardearaujo", info.getLicense().getUrl(), "License URL should match");

        Paths paths = openAPI.getPaths();
        assertNotNull(paths, "Paths object should not be null");

        assertFalse(paths.isEmpty(), "Paths should not be empty");

        assertTrue(paths.containsKey("/api/v1/menus"), "/api/v1/menus should exist");
        assertTrue(paths.containsKey("/api/v1/menus/{id}"), "/api/v1/menus/{id} should exist");
        assertTrue(paths.containsKey("/api/v1/menus/create"), "/api/v1/menus/create should exist");
        assertTrue(paths.containsKey("/api/v1/menus/update"), "/api/v1/menus/update should exist");
        assertTrue(paths.containsKey("/api/v1/menus/delete/{id}"), "/api/v1/menus/delete/{id} should exist");

        assertTrue(paths.containsKey("/api/v1/restaurants"), "/api/v1/restaurants should exist");
        assertTrue(paths.containsKey("/api/v1/restaurants/{id}"), "/api/v1/restaurants/{id} should exist");
        assertTrue(paths.containsKey("/api/v1/restaurants/create"), "/api/v1/restaurants/create should exist");
        assertTrue(paths.containsKey("/api/v1/restaurants/update"), "/api/v1/restaurants/update should exist");
        assertTrue(paths.containsKey("/api/v1/restaurants/delete/{id}"), "/api/v1/restaurants/delete/{id} should exist");

        assertTrue(paths.containsKey("/api/v1/users"), "/api/v1/users should exist");
        assertTrue(paths.containsKey("/api/v1/users/{id}"), "/api/v1/users/{id} should exist");
        assertTrue(paths.containsKey("/api/v1/users/create"), "/api/v1/users/create should exist");
        assertTrue(paths.containsKey("/api/v1/users/update"), "/api/v1/users/update should exist");
        assertTrue(paths.containsKey("/api/v1/users/delete/{id}"), "/api/v1/users/delete/{id} should exist");

        assertTrue(paths.containsKey("/api/v1/roles"), "/api/v1/roles should exist");
        assertTrue(paths.containsKey("/api/v1/roles/{id}"), "/api/v1/roles/{id} should exist");
        assertTrue(paths.containsKey("/api/v1/roles/create"), "/api/v1/roles/create should exist");
        assertTrue(paths.containsKey("/api/v1/roles/update"), "/api/v1/roles/update should exist");
        assertTrue(paths.containsKey("/api/v1/roles/delete/{id}"), "/api/v1/roles/delete/{id} should exist");


        // Opcional: Verificar detalhes de uma operação específica
        PathItem menuGetPathItem = paths.get("/api/v1/menus");
        assertNotNull(menuGetPathItem, "PathItem for /api/v1/menus should not be null");
        Operation getMenusOp = menuGetPathItem.getGet();
        assertNotNull(getMenusOp, "GET operation for /api/v1/menus should exist");
        assertEquals("Lista todos os menus", getMenusOp.getSummary(), "Summary for GET /api/v1/menus should match");
        assertTrue(getMenusOp.getTags().contains("Menu API"), "Tags should contain 'Menu API'");
        assertEquals("getAllMenus", getMenusOp.getOperationId(), "OperationId should match");
        assertNotNull(getMenusOp.getResponses().get("200"), "200 response should exist for GET /api/v1/menus");

        // Opcional: Verificar uma operação POST
        PathItem saveMenuPathItem = paths.get("/api/v1/menus/create");
        assertNotNull(saveMenuPathItem, "PathItem for /api/v1/menus/create should not be null");
        Operation saveMenuOp = saveMenuPathItem.getPost();
        assertNotNull(saveMenuOp, "POST operation for /api/v1/menus/create should exist");
        assertEquals("Salva um novo menu", saveMenuOp.getSummary(), "Summary for POST /api/v1/menus/create should match");
        assertEquals("saveMenu", saveMenuOp.getOperationId(), "OperationId should match");
        assertNotNull(saveMenuOp.getResponses().get("201"), "201 response should exist for POST /api/v1/menus/create");
    }


}
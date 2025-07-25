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

    }


}
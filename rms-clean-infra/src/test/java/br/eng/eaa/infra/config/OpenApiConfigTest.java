package br.eng.eaa.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.customizers.OpenApiCustomizer;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpenApiConfigTest {

    @InjectMocks
    private OpenApiConfig openApiConfig;

    @Mock
    private OpenAPI mockOpenAPI;

    @Mock
    private Components mockComponents;

    @BeforeEach
    void setUp() {
        when(mockOpenAPI.getComponents()).thenReturn(mockComponents);
    }

    @Test
    @DisplayName("Should not throw exception when components are null")
    void disableSchemas_componentsNull_shouldNotThrowException() {
        // When
        when(mockOpenAPI.getComponents()).thenReturn(null);
        OpenApiCustomizer customizer = openApiConfig.disableSchemas();
        customizer.customise(mockOpenAPI);
        // Then
        verify(mockOpenAPI, times(1)).getComponents();
        verifyNoInteractions(mockComponents);
    }

    @Test
    @DisplayName("Should disable schemas when components are present")
    void disableSchemas_componentsPresent_shouldSetSchemasToNull() {
        OpenApiCustomizer customizer = openApiConfig.disableSchemas();
        customizer.customise(mockOpenAPI);
        verify(mockOpenAPI, times(1)).getComponents();
        verify(mockComponents, times(1)).setSchemas(null);
    }

}
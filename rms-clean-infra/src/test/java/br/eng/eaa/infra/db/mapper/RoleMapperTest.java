package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.infra.db.dto.request.RoleRequestDto;
import br.eng.eaa.infra.db.dto.response.RoleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

    private UUID id;
    private String name;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "ADMIN";
    }

    @Test
    @DisplayName("Deve mapear RoleRequestDto para RoleRequest corretamente")
    void testMapRoleRequestDtoToRoleRequest() {
        // Arrange

        RoleRequestDto roleRequestDto = new RoleRequestDto(id, name);

        // Act
        RoleRequest roleRequest = RoleMapper.toRequest(roleRequestDto);

        // Assert
        assertNotNull(roleRequest);
        assertEquals(id, roleRequest.getId());
        assertEquals(name, roleRequest.getName());
    }

    @Test
    @DisplayName("Deve mapear RoleResponse para RoleResponseDto corretamente")
    void testMapRoleResponseToRoleResponseDto() {
        // Arrange
        RoleResponse roleResponse = new RoleResponse(id, name);

        // Act
        RoleResponseDto roleRequestDto = RoleMapper.toDto(roleResponse);

        // Assert
        assertNotNull(roleRequestDto);
        assertEquals(id, roleRequestDto.id());
        assertEquals(name, roleRequestDto.name());
    }

    @Test
    @DisplayName("Deve lançar exceção quando chamar toDto com RoleResponse null")
    void testToDtoWithNullRoleResponse() {
        // Arrange
        RoleResponse roleResponse = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> RoleMapper.toDto(roleResponse));
    }

    @Test
    @DisplayName("Deve lançar exceção quando chamar toRequest com RoleRequestDto null")
    void testToRequestWithNullRoleRequestDto() {
        // Arrange
        RoleRequestDto roleRequestDto = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> RoleMapper.toRequest(roleRequestDto));
    }
}
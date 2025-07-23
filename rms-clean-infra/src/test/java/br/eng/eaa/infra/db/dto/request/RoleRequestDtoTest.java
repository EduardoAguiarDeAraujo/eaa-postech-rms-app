package br.eng.eaa.infra.db.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoleRequestDtoTest {

    @Test
    @DisplayName("Deve retornar um RoleRequestDto com os valores corretos")
    void deveRetornarRoleRequestDtoComOsValoresCorretos() {
        // Arrange
        UUID id = UUID.randomUUID();
        String name = "ADMIN";

        // Act
        RoleRequestDto roleRequestDto = new RoleRequestDto(id, name);

        // Assert
        assertEquals(name, roleRequestDto.name());
        assertEquals(id, roleRequestDto.id());
    }

}
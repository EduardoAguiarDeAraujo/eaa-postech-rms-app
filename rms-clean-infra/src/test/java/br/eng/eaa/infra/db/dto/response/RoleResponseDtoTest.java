package br.eng.eaa.infra.db.dto.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoleResponseDtoTest {

    @Test
    @DisplayName("Deve retornar RoleResponseDto com os valores corretos")
    void deveRetornarRoleResponseDtoComOsValoresCorretos() {
        // Arrange
        UUID id = UUID.randomUUID();
        String name = "ADMIN";

        // Act
        RoleResponseDto roleResponseDto = new RoleResponseDto(id, name);

        // Assert
        assertEquals(id, roleResponseDto.id());
        assertEquals(name, roleResponseDto.name());
    }

}
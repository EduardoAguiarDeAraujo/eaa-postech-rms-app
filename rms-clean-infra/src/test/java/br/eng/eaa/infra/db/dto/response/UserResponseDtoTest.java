package br.eng.eaa.infra.db.dto.response;

import br.eng.eaa.infra.db.entity.RoleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseDtoTest {

    @Test
    @DisplayName("Test UserResponseDto creation")
    void testUserResponseDtoCreation() {
        // Given
        var id = UUID.randomUUID();
        var userName = "Eduardo";
        var roles = List.of(new RoleEntity(UUID.randomUUID(), "ADMIN"));

        // When
        var userResponseDto = new UserResponseDto(id, userName, roles);

        // Then
        assertEquals(id, userResponseDto.id());
        assertEquals(userName, userResponseDto.userName());
        assertEquals(roles, userResponseDto.roles());
    }

}
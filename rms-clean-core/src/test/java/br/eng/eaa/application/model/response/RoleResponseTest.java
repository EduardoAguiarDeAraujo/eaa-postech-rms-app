package br.eng.eaa.application.model.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleResponseTest {

    @Test
    @DisplayName("Deve retornar um RoleResponse válido")
    void shouldReturnValidRoleResponse() {
        UUID id = UUID.randomUUID();
        RoleResponse roleResponse = new RoleResponse(id, "ADMIN");

        assertNotNull(roleResponse);
        assertEquals(id, roleResponse.getId());
        assertEquals("ADMIN", roleResponse.getName());

        System.out.printf("RoleResponse válido - id: %s, name: %s%n", roleResponse.getId(), roleResponse.getName());
    }

}
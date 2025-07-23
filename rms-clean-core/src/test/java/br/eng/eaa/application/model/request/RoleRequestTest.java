package br.eng.eaa.application.model.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleRequestTest {

    @Test
    @DisplayName("Deve retornar um RoleRequest válido")
    void shouldReturnValidRoleRequest() {
        RoleRequest roleRequest = new RoleRequest("ADMIN");

        assertNotNull(roleRequest);
        assertEquals("ADMIN", roleRequest.getName());

        System.out.printf("RoleRequest válido - name: %s%n", roleRequest.getName());
    }

    @Test
    @DisplayName("Deve retornar um RoleRequest válido com nome e id")
    void shouldReturnInvalidRoleRequestWithNullName() {
        UUID id = UUID.randomUUID();
        RoleRequest roleRequest = new RoleRequest(id, "ADMIN");

        assertNotNull(roleRequest);
        assertEquals(id, roleRequest.getId());
        assertEquals("ADMIN", roleRequest.getName());

        System.out.printf("RoleRequest inválido - name: %s%n", roleRequest.getName());
    }

}
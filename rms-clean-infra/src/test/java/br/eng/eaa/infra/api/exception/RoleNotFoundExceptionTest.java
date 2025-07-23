package br.eng.eaa.infra.api.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleNotFoundExceptionTest {

    @Test
    @DisplayName("RoleNotFoundException should have the correct message")
    void testRoleNotFoundExceptionMessage() {
        String expectedMessage = "Role not found";
        RoleNotFoundException exception = new RoleNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

}
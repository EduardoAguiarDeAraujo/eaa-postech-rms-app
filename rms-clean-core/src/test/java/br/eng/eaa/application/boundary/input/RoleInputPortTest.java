package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RoleInputPortTest {

    private RoleInputPort roleInputPort;
    private UUID id;
    private String name;

    @BeforeEach
    void setUp() {
        this.roleInputPort = Mockito.mock(RoleInputPort.class);
        this.id = UUID.randomUUID();
        this.name = "ADMIN";
    }

    @Test
    @DisplayName("Deve salvar um Role com sucesso")
    void shouldSaveRoleSuccessfully() {
        // Given
        RoleRequest roleRequest = new RoleRequest(id, name);
        RoleResponse roleResponse = new RoleResponse(id, name);

        // When
        when(roleInputPort.save(roleRequest)).thenReturn(roleResponse);
        RoleResponse actualRole = roleInputPort.save(roleRequest);

        // Then
        assertNotNull(actualRole);
        assertEquals(roleRequest.getId(), actualRole.getId());
        assertEquals(roleRequest.getName(), actualRole.getName());
    }


}
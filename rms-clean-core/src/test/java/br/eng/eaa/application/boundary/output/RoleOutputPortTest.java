package br.eng.eaa.application.boundary.output;

import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RoleOutputPortTest {

    private RoleOutputPort roleOutputPort;
    private UUID id;
    private String name;

    @BeforeEach
    void setUp() {
        this.roleOutputPort = Mockito.mock(RoleOutputPort.class);
        this.id = UUID.randomUUID();
        this.name = "ADMIN";
    }

    @Test
    @DisplayName("Deve salvar um Role com sucesso")
    void shouldSaveRoleSuccessfully() {
        // Given
        Role role = new Role(id, name);
        RoleResponse roleResponse = new RoleResponse(id, name);

        // When
        when(roleOutputPort.execute(role)).thenReturn(roleResponse);
        RoleResponse actualRole = roleOutputPort.execute(role);

        // Then
        assertNotNull(actualRole);
        assertEquals(role.getId(), actualRole.getId());
        assertEquals(role.getName(), actualRole.getName());
    }

}
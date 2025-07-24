package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IRoleGatewayTest {

    private IRoleGateway roleGateway;
    private UUID id;
    private String name;

    @BeforeEach
    void setUp() {
        this.roleGateway = Mockito.mock(IRoleGateway.class);
        this.id = UUID.randomUUID();
        this.name = "ADMIN";
    }

    @Test
    @DisplayName("Deve salvar um Role com sucesso")
    void shouldSaveRoleSuccessfully() {
        // Given
        Role role = new Role(id, name);

        // When
        when(roleGateway.save(role)).thenReturn(role);
        Role actualRole = roleGateway.save(role);

        // Then
        assertNotNull(actualRole);
        assertEquals(role.getId(), actualRole.getId());
        assertEquals(role.getName(), actualRole.getName());
    }

    @Test
    @DisplayName("Deve atualizar um role com sucesso")
    void shouldUpdateRoleSuccessfully() {
        // Given
        Role role = new Role(id, name);

        // When
        when(roleGateway.update(role)).thenReturn(role);
        Role updateRole = roleGateway.update(role);

        // Then
        assertNotNull(updateRole);
        assertEquals(role.getId(), updateRole.getId());
        assertEquals(role.getName(), updateRole.getName());
    }

    @Test
    @DisplayName("Deve retornar um role por id")
    void shouldReturnRoleById() {
        //Given
        Role role = new Role(id, name);

        // When
        when(roleGateway.findById(id)).thenReturn(role);
        Role actualRole = roleGateway.findById(id);

        // Then
        assertNotNull(actualRole);
        assertEquals(role.getId(), actualRole.getId());
        assertEquals(role.getName(), actualRole.getName());
    }


}
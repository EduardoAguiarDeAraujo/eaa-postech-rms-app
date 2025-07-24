package br.eng.eaa.application.model.mapper;

import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoleDomainMapperTest {

    @Test
    @DisplayName("Deve mapear RoleRequest para Role")
    void shouldMapRoleRequestToRole() {
        // Given
        RoleRequest roleRequest = new RoleRequest("ADMIN");

        // When
        Role role = RoleDomainMapper.toRole(roleRequest);

        // Then
        assertNotNull(role);
        assertEquals("ADMIN", role.getName());
        System.out.printf("Role mapeado: id %s, RoleName: %s %n", role.getId(), role.getName());
    }

    @Test
    @DisplayName("Deve mapear Role para RoleResponse")
    void shouldMapRoleToRoleRequest() {
        // Given
        Role role = new Role("OWNER");

        // When
        RoleResponse roleResponse = RoleDomainMapper.toResponse(role);

        // Then
        assertNotNull(roleResponse);
        assertEquals(role.getName(), roleResponse.getName());
        System.out.printf("RoleRequest mapeado: id %s, RoleName: %s %n", roleResponse.getId(), roleResponse.getName());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao mapear RoleRequest nulo")
    void shouldThrowExceptionWhenMappingNullRoleRequest() {
        // Given
        RoleRequest roleRequest = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> RoleDomainMapper.toRole(roleRequest));
        System.out.println("Exceção lançada ao mapear RoleRequest nulo");
    }

    @Test
    @DisplayName("Deve criação de Role válido RoleResponse.getId() nulo")
    void shouldCreateValidRoleWithNullId() {
        // Given
        RoleRequest roleRequest = new RoleRequest("ADMIN");
        roleRequest.setId(null); // Simulating a null ID

        // When
        Role role = RoleDomainMapper.toRole(roleRequest);

        // Then
        assertNotNull(role);
        assertNotNull(role.getId()); // ID should be generated
        assertEquals("ADMIN", role.getName());
        System.out.printf("Role criado com ID gerado: id %s, RoleName: %s %n", role.getId(), role.getName());
    }

    @Test
    @DisplayName("Deve criar RoleMapper pelo construtor padrão")
    void shouldCreateRoleUsingDefaultConstructor() {
        // Given
        RoleDomainMapper roleDomainMapper = new RoleDomainMapper();

        // Then
        assertNotNull(roleDomainMapper);
    }

    @Test
    @DisplayName("Deve mapear RoleRequest para Role com id e nome valido")
    void shouldMapRoleRequestToRoleWithIdAndName() {
        // Given
        RoleRequest roleRequest = new RoleRequest(UUID.randomUUID(), "ADMIN" );

        // When
        Role role = RoleDomainMapper.toRole(roleRequest);

        // Then
        assertNotNull(role);
        assertEquals(roleRequest.getId(), role.getId());
        assertEquals(roleRequest.getName(), role.getName());
        System.out.printf("Role mapeado com ID e nome: id %s, RoleName: %s %n", role.getId(), role.getName());
    }


}
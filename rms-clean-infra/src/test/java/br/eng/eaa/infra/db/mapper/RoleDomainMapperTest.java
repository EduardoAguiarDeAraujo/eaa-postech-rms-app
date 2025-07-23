package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.db.entity.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleDomainMapperTest {


    private List<RoleEntity> rolesEntity;
    private List<Role> rolesDomain;

    @BeforeEach
    void setUp() {
        rolesEntity = List.of(
                new RoleEntity(UUID.randomUUID(), "ADMIN"),
                new RoleEntity(UUID.randomUUID(), "OWNER"));

        rolesDomain = List.of(
                new Role(UUID.randomUUID(), "ADMIN"),
                new Role(UUID.randomUUID(), "OWNER")
        );
    }

    @Test
    @DisplayName("should map RoleEntity to RoleDomain correctly")
    void shouldMapRoleEntityToRoleDomain() {
        // Given
        var roleEntity = rolesEntity.get(0);

        // When
        var roleDomain = RoleMapper.toRoleDomain(roleEntity);

        // Then
        assertEquals(roleEntity.getId(), roleDomain.getId());
        assertEquals(roleEntity.getName().toString(), roleDomain.getName().toString());
    }

    @Test
    @DisplayName("should map RoleDomain to RoleEntity correctly")
    void shouldMapRoleDomainToRoleEntity() {
        // Given
        var roleDomain = rolesDomain.get(0);

        // When
        var roleEntity = RoleMapper.toRoleEntity(roleDomain);

        // Then
        assertEquals(roleDomain.getId(), roleEntity.getId());
        assertEquals(roleDomain.getName().toString(), roleEntity.getName().toString());
    }

    @Test
    @DisplayName("should return a exception when RoleEntity is null")
    void shouldReturnExceptionWhenRoleEntityIsNull() {
        // Given
        RoleEntity roleEntity = null;

        // When & Then
        try {
            RoleMapper.toRoleDomain(roleEntity);
        } catch (IllegalArgumentException e) {
            assertEquals("RoleEntity must be not null", e.getMessage());
        }
    }

    @Test
    @DisplayName("should return a exception when RoleDomain is null")
    void shouldReturnExceptionWhenRoleDomainIsNull() {
        // Given
        Role role = null;

        // When & Then
        try {
            RoleMapper.toRoleEntity(role);
        } catch (IllegalArgumentException e) {
            assertEquals("Role must be not null", e.getMessage());
        }
    }

    @Test
    @DisplayName("should create a RoleEntity with the correct values")
    void shouldCreateRoleEntityWithCorrectValues() {
        // Given
        var role = rolesDomain.get(0);

        // When
        var roleEntity = RoleMapper.toRoleEntity(role);

        // Then
        assertEquals(role.getId(), roleEntity.getId());
        assertEquals(role.getName().toString(), roleEntity.getName().toString());
    }

    @Test
    @DisplayName("should create a instance of RoleMapper correctly")
    void shouldCreateInstanceOfRoleMapperCorrectly() {
        // Given
        RoleMapper roleMapper = new RoleMapper();

        // When & Then
        assertEquals(RoleMapper.class, roleMapper.getClass());
    }

}
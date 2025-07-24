package br.eng.eaa.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    UUID id;
    String name;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "ADMIN";
    }

    @Test
    @DisplayName("Deve retornar um role válido construtor 2")
    void shouldReturnValidRole() {
        Role role = new Role(id, name);
        assertNotNull(role);
        assertEquals(id, role.getId());
        System.out.printf("Role válido: id %s, RoleName: %s %n", role.getId(), role.getName());
    }

    @Test
    @DisplayName("Deve retornar um role válido construtor 1")
    void shouldReturnValidRole2() {
        Role role = new Role(name);

        assertNotNull(role);
        assertEquals(name, role.getName());
        assertNotNull(role.getId());

        System.out.printf("Role válido: id %s, RoleName: %s %n", role.getId(), role.getName());
    }

    @Test
    @DisplayName("Deve retornar exception para RoleName inválido")
    void shouldReturnExceptionForInvalidRoleName() {
        String invalidRoleName = null;
        assertThrows(IllegalArgumentException.class, () -> new Role(id, invalidRoleName));
    }

    @Test
    @DisplayName("Deve retornar exception para enum inválido")
    void shouldReturnExceptionForNulldEnumConstructor1() {
        assertThrows(IllegalArgumentException.class, () -> new Role(null));
    }

    @Test
    @DisplayName("Deve retornar Role válido com id null e nome valido")
    void shouldReturnValidRoleWithNullId() {
        Role role = new Role(null, name);
        assertNotNull(role);
        assertNotNull(role.getId());
        assertEquals(name, role.getName());
        System.out.printf("Role válido com id null: id %s, RoleName: %s %n", role.getId(), role.getName());
    }

}
package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {

    @Test
    void testRoleEntityCreation() {
        RoleEntity roleEntity = new RoleEntity(UUID.randomUUID(), "ADMIN");
        assertNotNull(roleEntity);
    }

}
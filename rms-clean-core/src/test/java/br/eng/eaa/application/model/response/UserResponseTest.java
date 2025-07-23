package br.eng.eaa.application.model.response;

import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserResponseTest {

    private UUID id;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
    }

    @Test
    @DisplayName("Deve retornar um usuário válido")
    void shouldReturnValidUser(){
        User user = new User(id, "Eduardo", roles);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("Eduardo", user.getUserName());
        assertEquals(roles, user.getRoles());

        System.out.printf("Usuário Válido - id: %s, name: %s, roles: %s %n", user.getId(), user.getUserName(), user.getRoles() );
    }
  
}
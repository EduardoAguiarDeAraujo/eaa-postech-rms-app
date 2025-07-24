package br.eng.eaa.application.model.request;

import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserRequestTest {

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
        UserRequest user = new UserRequest("Eduardo", "MinhaSenhaForte10#", roles);

        assertNotNull(user);
        assertEquals("Eduardo", user.getUserName());
        assertEquals(roles, user.getRoles());
    }

}
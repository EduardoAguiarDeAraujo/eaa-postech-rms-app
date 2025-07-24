package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UUID id;
    private String userName;
    private String password;
    private List<RoleEntity> roles;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        userName = "Eduardo";
        password = "MinhaSenhaForte10#";
        roles = List.of(
                new RoleEntity(UUID.randomUUID(), "ADMIN"),
                new RoleEntity(UUID.randomUUID(), "OWNER")
        );
    }

    @Test
    @DisplayName("Deve retornar um usuário válido")
    void shouldReturnValidUserEntity() {
        UserEntity user = new UserEntity(id, userName, password, roles);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(userName, user.getUserName());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getRoles());

        System.out.printf("Usuário válido - Id: %s - %s %n", user.getId(), user.getUserName());
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when username is null, empty, or blank")
    void shouldReturnExceptionForInvalidUsername(String invalidUsername) {
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, invalidUsername, password, roles));
        System.out.printf("Invalid User - Username: '%s'%n", invalidUsername != null ? invalidUsername : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when password is null, empty, or blank")
    void shouldReturnExceptionForInvalidPassword(String invalidPassword) {
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, userName, invalidPassword, roles));
        System.out.printf("Invalid User - Password: '%s'%n", invalidPassword != null ? invalidPassword : "null");
    }

    @Test
    @DisplayName("Deve retornar um excetion para role null")
    void shouldReturnExceptionForRolesNull()  {
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, userName, password, null));
        System.out.printf("Usuário invalido - Roles null %n");
    }

    @Test
    @DisplayName("Deve retornar um excetion para id null")
    void shouldReturnExceptionForIdNull() {
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(null, userName, password, roles));
        System.out.printf("Usuário invalido - Id null %n");
    }

    @Test
    @DisplayName("Deve verificar se a classe UserEntity existe e pode ser referenciada")
    void shouldVerifyUserEntityClassReference() {
        assertEquals(UserEntity.class, UserEntity.class);
    }


}
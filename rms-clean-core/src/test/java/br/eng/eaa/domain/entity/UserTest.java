package br.eng.eaa.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UUID id;
    private List<Role> roles;
    private String validPassword;
    private String name;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
        this.validPassword = "MinhaSenhaForte10#";
        this.name = "Eduardo";
    }

    @Test
    @DisplayName("Deve retornar um usuário válido")
    void shouldReturnValidUser() {
        User user = new User("Eduardo", "MinhaSenhaForte10#");

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals("Eduardo", user.getUserName());
        assertEquals("MinhaSenhaForte10#", user.getPassword());
        assertNull(user.getRoles());

        System.out.printf("Usuário válido - Id: %s - %s %n", user.getId(), user.getUserName());
    }

    @Test
    @DisplayName("Deve criar usuário válido com contrutor de Id e userName")
    void shouldCreateValidUserWithIdAndUserName() {
        User user = new User(id, "Eduardo");

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("Eduardo", user.getUserName());
        assertNull(user.getPassword());
        assertNull(user.getRoles());
        System.out.printf("Usuário válido - Id: %s - %s %n", user.getId(), user.getUserName());
    }

    @Test
    @DisplayName("Deve retornar um usuário válido")
    void shouldReturnValidUserWithId(){
        User user = new User(id, "Eduardo", roles);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals("Eduardo", user.getUserName());
        assertNotNull(user.getRoles());
        assertEquals(2, user.getRoles().size());
        assertEquals(roles, user.getRoles());
        System.out.printf("Usuário válido - ID: %s, Username: %s, Roles: %s %n", user.getId(), user.getUserName(), user.getRoles());
    }

    @Test
    @DisplayName("Deve retornar exception para id null")
    void shouldReturnExceptionForIDNull(){
        UUID invalidId = null;
        assertThrows(IllegalArgumentException.class, () -> new User(invalidId, "Eduardo", roles));
        System.out.printf("Usuário invalido - ID null %n");
    }

    @Test
    @DisplayName("Deve retornar exception para role null")
    void shouldReturnExceptionForRoleNull(){
        List<Role> invalidRoles = null;
        assertThrows(IllegalArgumentException.class, () -> new User(id, "Eduardo", invalidRoles));
        System.out.printf("Usuário invalido - Roles null %n");
    }

    @ParameterizedTest
    @NullAndEmptySource // Fornece null e "" (string vazia)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Fornece strings em branco (espaços, tabs, quebras de linha)
    @DisplayName("Deve retornar exceção para username nulo, vazio ou em branco")
    void shouldReturnExceptionForInvalidUsername(String invalidUsername) {
        assertThrows(IllegalArgumentException.class, () -> new User(invalidUsername, validPassword));
        System.out.printf("Usuário inválido - Username: '%s'%n", invalidUsername != null ? invalidUsername : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Fornece null e "" (string vazia)
    @ValueSource(strings = {""," ", "   ", "\t", "\n"}) // Fornece strings em branco (espaços, tabs, quebras de linha)
    @DisplayName("Deve retornar exceção para username nulo, vazio ou em branco no construtor 2")
    void shouldReturnExceptionForInvalidUsernameConstructor2(String invalidUsername) {
        assertThrows(IllegalArgumentException.class, () -> new User(id, invalidUsername, roles));
        System.out.printf("Usuário inválido - Username (Construtor 2): '%s'%n", invalidUsername != null ? invalidUsername : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Fornece null e "" (string vazia)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Fornece strings em branco (espaços, tabs, quebras de linha)
    @DisplayName("Deve retornar exceção para senha nula, vazia ou em branco")
    void shouldReturnExceptionForInvalidPassword(String invalidPassword) {
        assertThrows(IllegalArgumentException.class, () -> new User(name, invalidPassword));
        System.out.printf("Usuário inválido - Senha: '%s'%n", invalidPassword != null ? invalidPassword : "null");
    }

    @ParameterizedTest
    @NullAndEmptySource // Provides 'null' and "" (empty string)
    @ValueSource(strings = {" ", "   ", "\t", "\n"}) // Provides blank strings (spaces, tabs, newlines)
    @DisplayName("Should throw an exception when password is null, empty, or blank in constructor 1")
    void shouldReturnExceptionForInvalidPasswordConstructor1(String invalidPassword) {
        assertThrows(IllegalArgumentException.class, () -> new User(name, invalidPassword, roles));
    }
}
package br.eng.eaa.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UUID id;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
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
    @DisplayName("Deve retornar exception para username com nome null ")
    void shouldReturnExceptionForUserNameNull() {
        String invalidName = null;
        assertThrows(IllegalArgumentException.class, () -> new User(invalidName, "MinhaSenhaForte10#"));
        System.out.printf("Usuário invalido - Username null %n");
    }


    @Test
    @DisplayName("Deve retornar exception para username vazio")
    void shouldReturnExceptionForUserNameEmpty() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new User(invalidName, "MinhaSenhaForte10#"));
        System.out.printf("Usuário invalido - Username empty %n");
    }

    @Test
    @DisplayName("Deve retornar exception para username em Branco")
    void shouldReturnExceptionForUserNameBlank() {
        String invalidName = "  ";
        assertThrows(IllegalArgumentException.class, () -> new User(invalidName, "MinhaSenhaForte10#"));
        System.out.printf("Usuário invalido - Username blank %n");
    }

    @Test
    @DisplayName("Deve retornar exception para password null ")
    void shouldReturnExceptionForPasswordNull() {
        String invalidPassword = null;
        assertThrows(IllegalArgumentException.class, () -> new User("Katia", invalidPassword));
        System.out.printf("Usuário invalido - Password null %n");
    }

    @Test
    @DisplayName("Deve retornar exception para password blank")
    void shouldReturnExceptionForPasswordBlank() {
        String invalidPassword = " ";
        assertThrows(IllegalArgumentException.class, () -> new User("Katia", invalidPassword));
        System.out.printf("Usuário invalido - Password blank %n");
    }

    @Test
    @DisplayName("Deve retornar exception para password empty")
    void shouldReturnExceptionForPasswordEmpty() {
        String invalidPassword = "";
        assertThrows(IllegalArgumentException.class, () -> new User("Katia", invalidPassword));
        System.out.printf("Usuário invalido - Password empty %n");
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

    @Test
    @DisplayName("Deve retornar exception para username null construtor 2")
    void shouldReturnExceptionForUserNameNull2(){
        String invalidUsername = null;
        assertThrows(IllegalArgumentException.class, () -> new User(id, invalidUsername, roles));
        System.out.printf("Usuário invalido - Username null %n");
    }

    @Test
    @DisplayName("Deve retornar exception para username blank construtor 2")
    void shouldReturnExceptionForUsernameBlank2(){
        String invalidUsername = " ";
        assertThrows(IllegalArgumentException.class, () -> new User(id, invalidUsername, roles));
        System.out.printf("Usuário invalido - Username blank %n");
    }

    @Test
    @DisplayName("Deve retornar exception para username empty construtor 2")
    void shouldReturnExceptionForUsernameEmpty2(){
        String invalidUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new User(id, invalidUsername, roles));
        System.out.printf("Usuário invalido - Username blank %n");
    }

    @Test
    @DisplayName("Deve retornar erro quando parâmetos nulos no contrustor 1")
    void shouldReturnExceptionForParameterNullConstructor1(){
        String name = null;
        String password = null;
        List<Role> roles = null;
        assertThrows(IllegalArgumentException.class, () -> new User(name, password, roles));
    }

    @Test
    @DisplayName("Deve retornar erro quando parâmetos nulos no contrustor 1")
    void shouldReturnExceptionForParameterEmptyConstructor1(){
        String name = "";
        String password = "";
        assertThrows(IllegalArgumentException.class, () -> new User(name, password, roles));
    }

    @Test
    @DisplayName("Deve retornar erro quando parâmetos nulos no contrustor 1")
    void shouldReturnExceptionForParameterBlankConstructor1(){
        String name = "  ";
        String password = "  ";
        assertThrows(IllegalArgumentException.class, () -> new User(name, password, roles));
    }

    @Test
    @DisplayName("Deve retornar erro quando parametros nulos construtor 2")
    void shouldReturnExceptionForParameterNullConstructor2(){
        UUID id = null;
        String name = null;
        String password = null;
        List<Role> roles = null;
        assertThrows(IllegalArgumentException.class, () -> new User(id, name, password, roles));
    }

    @Test
    @DisplayName("Deve retornar erro quando parametros nulos construtor 2")
    void shouldReturnExceptionForParameterEmptyConstructor2(){
        UUID id = UUID.randomUUID();
        String name = "";
        String password = "";
        List<Role> roles = List.of(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "ADMIN"));
        assertThrows(IllegalArgumentException.class, () -> new User(id, name, password, roles));
    }

    @Test
    @DisplayName("Deve retornar erro quando parametros brancos construtor 2")
    void shouldReturnExceptionForParameterBlankConstructor2(){
        UUID id = UUID.randomUUID();
        String name = "  ";
        String password = "  ";
        List<Role> roles = List.of(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
        assertThrows(IllegalArgumentException.class, () -> new User(id, name, password, roles));
    }

}
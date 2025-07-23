package br.eng.eaa.infra.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Deve retornar um exception para username com nome null")
    void shouldReturnExceptionForUserNameNull() {
        String invalidName = null;
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, invalidName, password, roles));
        System.out.printf("Usuário invalido - Username null %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para username vazio")
    void shouldReturnExceptionForUserNameEmpty() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, invalidName, password, roles));
        System.out.printf("Usuário invalido - Username empty %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para username em branco")
    void shouldReturnExceptionForUserNameBlank() {
        String invalidName = "  ";
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, invalidName, password, roles));
        System.out.printf("Usuário invalido - Username blank %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para password null")
    void shouldReturnExceptionForPasswordNull() {
        String invalidPassword = null;
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, userName, invalidPassword, roles));
        System.out.printf("Usuário invalido - Password null %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para password vazio")
    void shouldReturnExceptionForPasswordEmpty() {
        String invalidPassword = "";
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, userName, invalidPassword, roles));
        System.out.printf("Usuário invalido - Password empty %n");
    }

    @Test
    @DisplayName("Deve retornar um exception para password em branco")
    void shouldReturnExceptionForPasswordBlank() {
        String invalidPassword = " ";
        assertThrows(IllegalArgumentException.class, () -> new UserEntity(id, userName, invalidPassword, roles));
        System.out.printf("Usuário invalido - Password blank %n");
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
    @DisplayName("Deve criar uma instancia de UserEntity corretamente")
    void shouldCreateUserEntityInstanceCorrectly() {
        //Given
        UserEntity user = new UserEntity();

        //When & Then
        assertEquals(UserEntity.class, user.getClass());
    }


}
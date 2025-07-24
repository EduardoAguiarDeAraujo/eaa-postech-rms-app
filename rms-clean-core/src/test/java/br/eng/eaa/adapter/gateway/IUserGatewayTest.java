package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IUserGatewayTest {

    private IUserGateway userGateway;
    private UUID id;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
        this.userGateway = Mockito.mock(IUserGateway.class);
    }

    @Test
    @DisplayName("Deve salvar um usuário")
    void givenAValidUser_whenCallsSave_thenReturnSavedUser() {
        // GIVEN
        User aUser = new User(id, "Eduardo", roles); // User sem ID
        User savedUser = new User(id, "Eduardo", roles); // User sem ID

        // WHEN
        when(userGateway.save(aUser)).thenReturn(savedUser);
        User actualUser = userGateway.save(aUser);

        // THEN
        verify(userGateway, times(1)).save(aUser);
        assertEquals(savedUser, actualUser);
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    void givenAValidUser_whenCallsUpdate_thenReturnUpdatedUser() {
        // GIVEN
        User updatedUser  = new User(id, "Eduardo Novo", roles); // User sem ID

        // WHEN
        when(userGateway.update(updatedUser)).thenReturn(updatedUser);
        User actualUser = userGateway.update(updatedUser);

        // THEN
        verify(userGateway, times(1)).update(updatedUser);
        assertEquals(updatedUser, actualUser);
    }

    @Test
    @DisplayName("Deve retornar um usuário por ID")
    void givenAValidId_whenCallsFindById_thenReturnUser() {
        // GIVEN
        User expectedUser = new User(id, "Eduardo Encontrado", roles);

        // WHEN
        when(userGateway.findById(id)).thenReturn(expectedUser);
        User actualUser = userGateway.findById(id);

        // THEN
        verify(userGateway, times(1)).findById(id);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Deve retornar null para id inválido")
    void givenAnInvalidId_whenCallsFindById_thenReturnNull() {
        // GIVEN
        UUID invalidId = UUID.randomUUID();

        // WHEN
        when(userGateway.findById(invalidId)).thenReturn(null);
        User actualUser = userGateway.findById(invalidId);

        // THEN
        verify(userGateway, times(1)).findById(invalidId);
        assertNull(actualUser);
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários")
    void whenCallsFindAll_thenReturnAllUsers() {
        // GIVEN
        List<User> expectedUsers = Arrays.asList(
                new User(UUID.randomUUID(), "Eduardo", roles),
                new User(UUID.randomUUID(), "Katia", roles)
        );

        // WHEN
        when(userGateway.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userGateway.findAll();

        // THEN
        verify(userGateway, times(1)).findAll();
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertTrue(actualUsers.containsAll(expectedUsers));
    }

    @Test
    @DisplayName("Deve retornar true para exclusão para id válido")
    void givenAValidId_whenCallsDelete_thenReturnTrue() {
        // GIVEN
        UUID idToDelete = UUID.randomUUID();

        // WHEN
        when(userGateway.delete(idToDelete)).thenReturn(true);
        Boolean actualResult = userGateway.delete(idToDelete);

        // THEN
        verify(userGateway, times(1)).delete(idToDelete);
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Deve retornar false para exclusão para id inválido")
    void givenAnInvalidId_whenCallsDelete_thenReturnFalse() {
        // GIVEN
        UUID invalidId = UUID.randomUUID();

        // WHEN
        when(userGateway.delete(invalidId)).thenReturn(false);
        Boolean actualResult = userGateway.delete(invalidId);

        // THEN
        verify(userGateway, times(1)).delete(invalidId);
        assertFalse(actualResult);
    }






}
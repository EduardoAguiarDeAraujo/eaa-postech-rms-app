package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IUserGateway;
import br.eng.eaa.application.boundary.output.UserOutputPort;
import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserInteractorTest {

    private IUserGateway userGateway;
    private UserOutputPort userOutput;
    private UserInteractor userInteractor;
    private UUID id;
    private String userName;
    private String password;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.userGateway = Mockito.mock(IUserGateway.class);
        this.userOutput = Mockito.mock(UserOutputPort.class);
        this.userInteractor = new UserInteractor(userGateway, userOutput);
        this.id = UUID.randomUUID();
        this.userName = "Eduardo";
        this.password = "MinhaSenhaForte10#";
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
    }

    @Test
    @DisplayName("Deve salvar um usuário válido")
    void shouldSavedValidUser() {
        //GIVEN
        UserRequest userRequest = new UserRequest( userName, password, roles);
        User savedUser = new User(userRequest.getUserName(), userRequest.getPassword(), userRequest.getRoles());
        UserResponse userResponse = new UserResponse(savedUser.getId(), savedUser.getUserName(), savedUser.getRoles());
        //WHEN
        when(userInteractor.save(userRequest)).thenReturn(userResponse);
        UserResponse actualResponse = userInteractor.save(userRequest);
        // THEN
        assertEquals(userResponse, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar usuário por ID válido")
    void shouldReturnUserById() {
        //GIVEN
        UUID id = UUID.randomUUID();
        UserResponse userResponse = new UserResponse(id, userName, roles);;
        //WHEN
        when(userInteractor.findById(id)).thenReturn(userResponse);
        UserResponse actualResponse = userInteractor.findById(id);
        // THEN
        assertEquals(userResponse, actualResponse);
    }

    @Test
    @DisplayName("Deve atualizar um usuário válido")
    void shouldUpdatedValidUser() {
        //GIVEN
        UserRequest userRequest = new UserRequest(UUID.randomUUID(), "Eduardo", "MinhaSenhaForte10#", roles);
        User updatedUser = new User(userRequest.getId(), userRequest.getUserName(), userRequest.getPassword(), userRequest.getRoles());
        UserResponse userResponse = new UserResponse(updatedUser.getId(), updatedUser.getUserName(), updatedUser.getRoles());
        //WHEN
        when(userInteractor.update(userRequest)).thenReturn(userResponse);
        UserResponse actualResponse = userInteractor.update(userRequest);
        // THEN
        assertEquals(userResponse, actualResponse);
    }

    @Test
    @DisplayName("Deve excluirusuário por ID válido")
    void shouldDeletedUserById() {
        //GIVEN
        UUID id = UUID.randomUUID();
        Boolean userResponse = true;;
        //WHEN
        when(userInteractor.delete(id)).thenReturn(userResponse);
        Boolean actualResponse = userInteractor.delete(id);
        // THEN
        assertEquals(userResponse, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar lista de usuários")
    void shouldReturnListAllUse() {
        //GIVEN
        List<UserResponse> userResponses = List.of(
                new UserResponse(UUID.randomUUID(), userName, roles),
                new UserResponse(UUID.randomUUID(), userName, roles)
        );
        //WHEN
        when(userInteractor.findAll()).thenReturn(userResponses);
        List<UserResponse> actualResponses = userInteractor.findAll();
        // THEN
        assertEquals(userResponses, actualResponses);
    }

    @Test
    @DisplayName("Deve criar um usuário válido com password null")
    void shouldCreateValidUserWithIdAndPasswordNull() {
        //GIVEN
        UserRequest userRequest = new UserRequest(id, userName, null, roles);
        User savedUser = new User(id, userName,roles);
        UserResponse userResponse = new UserResponse(savedUser.getId(), savedUser.getUserName(), savedUser.getRoles());

        //WHEN
        when(userInteractor.save(userRequest)).thenReturn(userResponse);
        UserResponse actualResponse = userInteractor.save(userRequest);
        // THEN
        assertEquals(userResponse, actualResponse);
    }

}
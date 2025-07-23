package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IUserGateway;
import br.eng.eaa.adapter.gateway.UserGatewayStub;
import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    private IUserGateway userGateway;
    private UserController userController;
    private UUID id;
    private String name;
    private String password;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.userGateway = new UserGatewayStub();
        this.userController = new UserController(userGateway);
        this.id = UUID.fromString("31d21f36-6edc-4f3d-9fda-879e09c739fe");
        this.name = "Eduardo";
        this.password = "MinhaSenhaForte10#";
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
    }

    @Test
    @DisplayName("Deve salvar um usuário válido")
    void givenAValidUser_whenCallsSave_thenReturnSavedUser(){
        //GIVEN
        UserRequest userRequest = new UserRequest(name,password,roles);
        UserResponse userResponse = new UserResponse(id,name,roles);

        //WHEN
        UserResponse actualResponse = userController.save(userRequest);

        //THEN
        assertEquals(userResponse.getId(), actualResponse.getId());
        assertEquals(userResponse.getUserName(), actualResponse.getUserName());
        assertEquals(userResponse.getRoles().toString(), actualResponse.getRoles().toString());
    }

    @Test
    @DisplayName("Deve atualizar um usuário válido")
    void givenAValidUserRequest_whenCallsUpdate_thenReturnUpdatedUserResponse() {
        //GIVEN
        UserRequest userRequest = new UserRequest(id,name,password, roles);
        UserResponse userResponse = new UserResponse(id,name,roles);

        //WHEN
        UserResponse actualResponse = userController.update(userRequest);

        //THEN
        assertEquals(userResponse.getId(), actualResponse.getId());
        assertEquals(userResponse.getUserName(), actualResponse.getUserName());
        assertEquals(userResponse.getRoles().toString(), actualResponse.getRoles().toString());
    }

    @Test
    @DisplayName("Deve retornar true na exclusão de um usuário por id válido")
    void givenAValidUserRequest_whenCallsDelete_thenReturnDeletedUserResponse() {
        //GIVEN
        Boolean isDeleted = true;
        UUID id = this.id;
        //WHEN
        Boolean actualResponse = userController.delete(id);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar false na exclusão de um usuário por id válido")
    void givenAInvalidUserRequest_whenCallsDelete_thenReturnDeletedUserResponse() {
        //GIVEN
        Boolean isDeleted = false;
        UUID id = UUID.randomUUID();
        //WHEN
        Boolean actualResponse = userController.delete(id);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um usuário válido quando pesquisar por id válido")
    void givenAValidMenuRequest_whenCallsFindById_thenReturnAMenuResponse() {
        //GIVEN
        UUID id = this.id;
        UserResponse userResponse = new UserResponse(id,name,roles);

        //WHEN
        UserResponse actualResponse = userController.findById(id);

        //THEN
        assertEquals(userResponse.getId(), actualResponse.getId());
        assertEquals(userResponse.getUserName(), actualResponse.getUserName());
        assertEquals(userResponse.getRoles().toString(), actualResponse.getRoles().toString());
    }

    @Test
    @DisplayName("Deve retornar um usuário válido quando pesquisar por id válido")
    void givenAValidMenuRequest_whenCallsFindAll_thenReturnAListOfMenuResponse() {
        //GIVEN
        List<UserResponse> userResponse = List.of(
                new UserResponse(id,name,roles),
                new UserResponse(id,name,roles)
        );

        //WHEN
        List<UserResponse> actualResponse = userController.findAll();

        //THEN
        assertEquals(userResponse.size(), actualResponse.size());
    }


}
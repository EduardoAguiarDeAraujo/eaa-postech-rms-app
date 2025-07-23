package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserPresenterTest {

    private UUID id;
    private String name;
    private List<Role> roles;


    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.name = "Eduardo";
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
    }

    @Test
    @DisplayName("Deve retornar um usuário válido")
    void shouldReturnValidUser() {
        //GIVEN
        User user = new User(id, name, roles);
        UserResponse userResponse = new UserResponse(id, name, roles);

        //WHEN
        UserResponse actualResponse = new  UserPresenter().execute(user);

        //THEN
        assertEquals(userResponse.getId(), actualResponse.getId());
        assertEquals(userResponse.getUserName(), actualResponse.getUserName());
        assertEquals(userResponse.getRoles(), actualResponse.getRoles());

    }

    @Test
    @DisplayName("Deve retornar um boolean = true")
    void shouldReturnTrue(){
        //GIVEN
        Boolean isDeleted = true;
        //WHEN
        Boolean actualResponse = new UserPresenter().execute(isDeleted);
        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar uma lista de usuários")
    void shouldReturnListAllUser() {
        //GIVEN
        List<User> users = List.of(
                new User(id,name,roles),
                new User(id,name,roles),
                new User(id,name,roles)
        );
        List<UserResponse> userResponses = List.of(
                new UserResponse(id,name,roles),
                new UserResponse(id,name,roles),
                new UserResponse(id,name,roles)
        );

        //WHEN
        List<UserResponse> actualResponses = new UserPresenter().execute(users);

        //THEN
        assertEquals(userResponses.size(), actualResponses.size());
    }



}
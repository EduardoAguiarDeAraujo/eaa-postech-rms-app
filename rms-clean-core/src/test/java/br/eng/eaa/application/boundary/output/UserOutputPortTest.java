package br.eng.eaa.application.boundary.output;

import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserOutputPortTest {

    private UserOutputPort userOutputPort;
    private UUID id;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
        this.userOutputPort = Mockito.mock(UserOutputPort.class);
    }

    @Test
    @DisplayName("Deve salvar um usuário")
    void givenAValidUser_whenCallsSave_thenReturnSavedUser() {
        // GIVEN
        User user = new User("Eduardo","MinhaSenhaForte10#", roles);
        UserResponse userResponse = new UserResponse(id, "Eduardo", roles);

        // WHEN
        when(userOutputPort.execute(user)).thenReturn(userResponse);
        UserResponse actualUser = userOutputPort.execute(user);

        // THEN
        verify(userOutputPort, times(1)).execute(user);
        assertEquals(userResponse.getId(), actualUser.getId());
        assertEquals(userResponse.getUserName(), actualUser.getUserName());
    }

}
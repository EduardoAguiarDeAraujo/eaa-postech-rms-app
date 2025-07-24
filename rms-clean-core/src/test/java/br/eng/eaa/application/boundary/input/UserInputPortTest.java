package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserInputPortTest {

    private UserInputPort userInputPort;
    private UUID id;
    private List<Role> roles;

    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
        this.userInputPort = Mockito.mock(UserInputPort.class);
    }

    @Test
    @DisplayName("Deve salvar um usuário")
    void givenAValidUser_whenCallsSave_thenReturnSavedUser() {
        // GIVEN
        UserRequest userRequest = new UserRequest("Eduardo","MinhaSenhaForte10#", roles);
        UserResponse userResponse = new UserResponse(id, "Eduardo", roles);

        // WHEN
        when(userInputPort.save(userRequest)).thenReturn(userResponse);
        UserResponse actualUser = userInputPort.save(userRequest);

        // THEN
        verify(userInputPort, times(1)).save(userRequest);
        assertEquals(userResponse.getId(), actualUser.getId());
        assertEquals(userResponse.getUserName(), actualUser.getUserName());
    }


}
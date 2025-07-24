package br.eng.eaa.infra.db.dto.request;

import br.eng.eaa.infra.db.entity.RoleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRequestDtoTest {

    @Test
    @DisplayName("Test UserRequestDto creation")
    void testUserRequestDtoCreation() {
        // Given
        var id = UUID.randomUUID();
        var userName = "Eduardo";
        var password = "MinhaSenhaForte10#";
        var roles = List.of(new RoleEntity(UUID.randomUUID(), "ADMIN"));

        // When
        var userRequestDto = new UserRequestDto(id, userName, password, roles);

        // Then
        assertEquals(id, userRequestDto.id());
        assertEquals(userName, userRequestDto.userName());
        assertEquals(password, userRequestDto.password());
        assertEquals(roles, userRequestDto.roles());
    }

    @Test
    @DisplayName("Deve apresentar o resultado do método getData()")
    void shouldShowGetDataMethod(){
        // Given
        var id = UUID.randomUUID();
        var userName = "Eduardo";
        var password = "MinhaSenhaForte10#";
        var roles = List.of(new RoleEntity(UUID.randomUUID(), "ADMIN"));
        String getData = "UserRequestDto{id=" + id + ", userName='"+ userName +"', roles="+ roles +"}";

        // When
        var userRequestDto = new UserRequestDto(id, userName, password, roles);

        // then
        assertEquals(getData, userRequestDto.getData());

    }
  
}
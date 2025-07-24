package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.db.dto.request.UserRequestDto;
import br.eng.eaa.infra.db.entity.RoleEntity;
import br.eng.eaa.infra.db.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserMapperTest {

    private UUID id;
    private String userName;
    private String password;
    private List<RoleEntity> rolesEntity;
    private List<Role> rolesDomain;

    @BeforeEach
    void setUp() {
        id = UUID.fromString("e0f279d7-5536-11f0-a29e-04bf1b4887e6");
        userName = "Eduardo";
        password = "MinhaSenhaForte10#";
        rolesEntity = List.of(
                new RoleEntity(UUID.randomUUID(), "ADMIN"),
                new RoleEntity(UUID.randomUUID(), "OWNER"));

        rolesDomain = List.of(
                new Role(UUID.randomUUID(), "ADMIN"),
                new Role(UUID.randomUUID(), "OWNER")
        );
    }

    @Test
    @DisplayName("should map UserEntity to UserDomain correctly")
    void shouldMapUserEntityToUserDto() {
        // Given
        var userEntity = new UserEntity(id, userName, password, rolesEntity);

        //When
        var userDomain = UserMapper.toDomain(userEntity);

        // Then
        assertEquals(id, userDomain.getId());
        assertEquals(userName, userDomain.getUserName());
        assertEquals(password, userDomain.getPassword());
        assertEquals(rolesDomain.size(), userDomain.getRoles().size());
    }

    @Test
    @DisplayName("should map UserDomain to UserEntity correctly")
    void shouldMapUserDomainToUserEntity() {
        // Given
        var userDomain = new User(id, userName, password, rolesDomain);

        // When
        var userEntity = UserMapper.toEntity(userDomain);

        // Then
        assertEquals(id, userEntity.getId());
        assertEquals(userName, userEntity.getUserName());
        assertEquals(password, userEntity.getPassword());
        assertEquals(rolesEntity.size(), userEntity.getRoles().size());
    }

    @Test
    @DisplayName("should map UserRequestDto to UserRequest correctly")
    void shouldMapUserRequestDtoToUserRequest() {
        // Given
        var userRequestDto = new UserRequestDto(id, userName, password, rolesEntity);

        // When
        var userRequest = UserMapper.toRequest(userRequestDto);

        // Then
        assertEquals(id, userRequest.getId());
        assertEquals(userName, userRequest.getUserName());
        assertEquals(password, userRequest.getPassword());
        assertEquals(rolesDomain.size(), userRequest.getRoles().size());
    }

    @Test
    @DisplayName("should map UserResponse to UserResponseDto correctly")
    void shouldMapUserResponseToUserResponseDto() {
        // Given
        var userResponse = new UserResponse(id, userName, rolesDomain);

        // When
        var userResponseDto = UserMapper.toDto(userResponse);

        // Then
        assertEquals(id, userResponseDto.id());
        assertEquals(userName, userResponseDto.userName());
        assertEquals(rolesEntity.size(), userResponseDto.roles().size());
    }

    @Test
    @DisplayName("should map User to UserResponseDto correctly")
    void shouldMapUserToUserResponseDto() {
        // Given
        var user = new User(id, userName, password, rolesDomain);

        // When
        var userResponseDto = UserMapper.toDto(user);

        // Then
        assertEquals(id, userResponseDto.id());
        assertEquals(userName, userResponseDto.userName());
        assertEquals(rolesEntity.size(), userResponseDto.roles().size());
    }

    @Test
    @DisplayName("should map User to UserEntity correctly")
    void shouldMapUserToUserEntity() {
        // Given
        var user = new User(id, userName, password, rolesDomain);

        // When
        var userEntity = UserMapper.toEntity(user);

        // Then
        assertEquals(id, userEntity.getId());
        assertEquals(userName, userEntity.getUserName());
        assertEquals(password, userEntity.getPassword());
        assertEquals(rolesEntity.size(), userEntity.getRoles().size());
    }

    @Test
    @DisplayName("should map User to UserEntity correctly with password null")
    void shouldMapUserToUserEntityWithPasswordNull() {
        // Given
        var user = new User(id, userName);

        // When
        var userEntity = UserMapper.toEntity(user);

        // Then
        assertEquals(id, userEntity.getId());
        assertEquals(userName, userEntity.getUserName());
    }

    @Test
    @DisplayName("should map UserEntity to UserDomain correctly with password null")
    void shouldMapUserEntityToUserDomainWithPasswordNull() {
        // Given
        var userEntity = new UserEntity(id, userName);

        // When
        var userDomain = UserMapper.toDomain(userEntity);

        // Then
        assertEquals(id, userDomain.getId());
        assertEquals(userName, userDomain.getUserName());
    }

    @Test
    @DisplayName("should return a exception to UserEntity null")
    void shouldReturnExceptionToUserEntityNull() {
        // Given
        UserEntity userEntity = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> UserMapper.toDomain(userEntity), "UserEntity must be not null");
    }

    @Test
    @DisplayName("should return a exception to UserDomain null")
    void shouldReturnExceptionToUserDomainNull() {
        // Given
        User user = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> UserMapper.toEntity(user), "User must be not null");
    }

    @Test
    @DisplayName("should return a exception to UserRequestDto null")
    void shouldReturnExceptionToUserRequestDtoNull() {
        // Given
        UserRequestDto userRequestDto = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> UserMapper.toRequest(userRequestDto), "UserRequestDto must be not null");
    }

    @Test
    @DisplayName("should return a exception to UserResponse null")
    void shouldReturnExceptionToUserResponseNull() {
        // Given
        UserResponse userResponse = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> UserMapper.toDto(userResponse), "UserResponse must be not null");
    }

    @Test
    @DisplayName("should return a exception to User null when mapping User to UserResponseDto")
    void shouldReturnExceptionToUserNullWhenMappingUserToUserResponseDto() {
        // Given
        User user = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> UserMapper.toDto(user), "User must be not null");
    }

}
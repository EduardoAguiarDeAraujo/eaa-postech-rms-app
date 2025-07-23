package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.UserController;
import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.db.dto.request.UserRequestDto;
import br.eng.eaa.infra.db.dto.response.UserResponseDto;
import br.eng.eaa.infra.db.entity.RoleEntity;
import br.eng.eaa.infra.db.mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserController userController;
    private MockedStatic<UserMapper> mockedUserMapper;
    private UserService userService;
    private UUID id;
    private String userName;
    private String password;
    private List<Role> rolesDomain;
    private List<RoleEntity> rolesEntity;

    @BeforeEach
    void setUp() {
        userController = Mockito.mock(UserController.class);
        mockedUserMapper = Mockito.mockStatic(UserMapper.class);
        userService = new UserService(userController);
        id = UUID.randomUUID();
        userName = "Eduardo";
        password = "MinhaSenhaForte10#";
        rolesDomain = List.of(
                new Role(UUID.randomUUID(), "ADMIN"),
                new Role(UUID.randomUUID(), "OWNER")
        );
        rolesEntity = List.of(
                new RoleEntity(UUID.randomUUID(), "ADMIN"),
                new RoleEntity(UUID.randomUUID(), "OWNER")
        );
    }

    @AfterEach
    void tearDown() {
        mockedUserMapper.close();
    }

    @Test
    @DisplayName("Deve criar um usuário com sucesso")
    void shouldSaveSuccessfully() {
        // Given
        var userRequest = new UserRequest(id, userName, password, rolesDomain);
        var userResponse = new UserResponse(id, userName, rolesDomain);
        var userRequestDto = new UserRequestDto(id, userName, password, rolesEntity);
        var userResponseDto = new UserResponseDto(id, userName, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toRequest(userRequestDto)).thenReturn(userRequest);
        mockedUserMapper.when(() -> UserMapper.toDto(userResponse)).thenReturn(userResponseDto);
        when(userController.save(userRequest)).thenReturn(userResponse);
        UserResponseDto createdUser = userService.save(userRequestDto);

        // Then
        verify(userController, times(1)).save(userRequest);
        assertNotNull(createdUser);
        assertEquals(userResponseDto.id(), createdUser.id());

        System.out.printf("Usuário criado com sucesso - Id: %s - %s %n", createdUser.id(), createdUser.userName());
    }

    @Test
    @DisplayName("Deve buscar um usuário por ID com sucesso")
    void shouldFindUserByIdSuccessfully() {
        // Given
        var userResponse = new UserResponse(id, userName, rolesDomain);
        var userResponseDto = new UserResponseDto(id, userName, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toDto(userResponse)).thenReturn(userResponseDto);
        when(userController.findById(id)).thenReturn(userResponse);
        UserResponseDto foundUser = userService.findById(id);

        // Then
        verify(userController, times(1)).findById(id);
        assertEquals(userResponseDto.id(), foundUser.id());

        System.out.printf("Usuário encontrado com sucesso - Id: %s - %s %n", foundUser.id(), foundUser.userName());
    }

    @Test
    @DisplayName("Deve atualizar um usuário com sucesso")
    void shouldUpdateUserSuccessfully() {
        // Given
        var userRequest = new UserRequest(id, userName, password, rolesDomain);
        var userResponse = new UserResponse(id, userName, rolesDomain);
        var userRequestDto = new UserRequestDto(id, userName, password, rolesEntity);
        var userResponseDto = new UserResponseDto(id, userName, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toRequest(userRequestDto)).thenReturn(userRequest);
        mockedUserMapper.when(() -> UserMapper.toDto(userResponse)).thenReturn(userResponseDto);
        when(userController.update(userRequest)).thenReturn(userResponse);
        UserResponseDto updatedUser = userService.update(userRequestDto);

        // Then
        verify(userController, times(1)).update(userRequest);
        assertEquals(userResponseDto.id(), updatedUser.id());

        System.out.printf("Usuário atualizado com sucesso - Id: %s - %s %n", updatedUser.id(), updatedUser.userName());
    }

    @Test
    @DisplayName("Deve deletar um usuário com sucesso")
    void shouldDeleteUserSuccessfully() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(userController.delete(id)).thenReturn(true);
        Boolean isDeleted = userService.delete(id);

        // Then
        verify(userController, times(1)).delete(id);
        assertEquals(true, isDeleted);

        System.out.printf("Usuário deletado com sucesso - Id: %s %n", id);
    }

    @Test
    @DisplayName("Deve buscar todos os usuários com sucesso")
    void shouldFindAllUsersSuccessfully() {
        // Given
        var userResponse1 = new UserResponse(UUID.randomUUID(), "User1", rolesDomain);
        var userResponse2 = new UserResponse(UUID.randomUUID(), "User2", rolesDomain);
        var userResponses = List.of(userResponse1, userResponse2);
        var userResponseDto1 = new UserResponseDto(userResponse1.getId(), userResponse1.getUserName(), rolesEntity);
        var userResponseDto2 = new UserResponseDto(userResponse2.getId(), userResponse2.getUserName(), rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toDto(userResponse1)).thenReturn(userResponseDto1);
        mockedUserMapper.when(() -> UserMapper.toDto(userResponse2)).thenReturn(userResponseDto2);
        when(userController.findAll()).thenReturn(userResponses);
        List<UserResponseDto> foundUsers = userService.findAll();

        // Then
        verify(userController, times(1)).findAll();
        mockedUserMapper.verify(() -> UserMapper.toDto(userResponse1), times(1));
        mockedUserMapper.verify(() -> UserMapper.toDto(userResponse2), times(1));

        System.out.printf("Usuários encontrados com sucesso - Total: %d %n", foundUsers.size());
    }


}
package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.api.exception.UserNotFoundException;
import br.eng.eaa.infra.db.entity.RoleEntity;
import br.eng.eaa.infra.db.entity.UserEntity;
import br.eng.eaa.infra.db.mapper.UserMapper;
import br.eng.eaa.infra.db.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserGatewayTest {

    private UserRepository userRepository;
    private MockedStatic<UserMapper> mockedUserMapper;
    private UserGateway userGateway;
    private UUID id;
    private String userName;
    private String password;
    private List<Role> rolesDomain;
    private List<RoleEntity> rolesEntity;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        mockedUserMapper = Mockito.mockStatic(UserMapper.class);
        userGateway = new UserGateway(userRepository);
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
    void shouldCreateUserSuccessfully() {
        // Given
        User userDomain = new User(id, userName, password, rolesDomain);
        UserEntity userEntity = new UserEntity(id, userName, password, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toEntity(userDomain)).thenReturn(userEntity);
        mockedUserMapper.when(() -> UserMapper.toDomain(userEntity)).thenReturn(userDomain);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        User savedUser = userGateway.save(userDomain);

        // Then
        verify(userRepository, times(1)).save(userEntity);
        assertEquals(userDomain.getId(), savedUser.getId());
        assertEquals(userDomain.getUserName(), savedUser.getUserName());

        System.out.printf("Usuário criado com sucesso - Id: %s - %s %n", savedUser.getId(), savedUser.getUserName());
    }

    @Test
    @DisplayName("Deve atualizar um usuário com sucesso")
    void shouldUpdateUserSuccessfully() {
        // Given
        User userDomain = new User(id, userName, password, rolesDomain);
        UserEntity userEntity = new UserEntity(id, userName, password, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toEntity(userDomain)).thenReturn(userEntity);
        mockedUserMapper.when(() -> UserMapper.toDomain(userEntity)).thenReturn(userDomain);
        when(userRepository.findById(userDomain.getId())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        User updatedUser = userGateway.update(userDomain);

        // Then
        verify(userRepository, times(1)).save(userEntity);
        verify(userRepository, times(1)).findById(userDomain.getId());
        assertEquals(userDomain.getUserName(), updatedUser.getUserName());

        System.out.printf("Usuário atualizado com sucesso - Id: %s - %s %n", updatedUser.getId(), updatedUser.getUserName());
    }

    @Test
    @DisplayName("Deve buscar um usuário por ID com sucesso")
    void shouldFindUserByIdSuccessfully() {
        // Given
        User userDomain = new User(id, userName, password, rolesDomain);
        UserEntity userEntity = new UserEntity(id, userName, password, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toEntity(userDomain)).thenReturn(userEntity);
        mockedUserMapper.when(() -> UserMapper.toDomain(userEntity)).thenReturn(userDomain);
        when(userRepository.findById(userDomain.getId())).thenReturn(Optional.of(userEntity));
        User foundUser = userGateway.findById(userDomain.getId());

        // Then
        verify(userRepository, times(1)).findById(userDomain.getId());
        assertEquals(userDomain.getId(), foundUser.getId());
        assertEquals(userDomain.getUserName(), foundUser.getUserName());

        System.out.printf("Usuário encontrado com sucesso - Id: %s - %s %n", foundUser.getId(), foundUser.getUserName());
    }

    @Test
    @DisplayName("Deve retornar null para um ID inválido")
    void shouldReturnNullForInvalidId() {
        // Given
        UUID invalidId = UUID.randomUUID();
        User userDomain = new User(id, userName, password, rolesDomain);
        UserEntity userEntity = new UserEntity(id, userName, password, rolesEntity);

        // When
        mockedUserMapper.when(() -> UserMapper.toEntity(userDomain)).thenReturn(userEntity);
        mockedUserMapper.when(() -> UserMapper.toDomain(userEntity)).thenReturn(userDomain);
        when(userRepository.findById(userDomain.getId())).thenReturn(Optional.empty());
        User foundUser = userGateway.findById(invalidId);

        // Then
        verify(userRepository, times(1)).findById(invalidId);
        assertEquals(null, foundUser);

        System.out.printf("Usuário não encontrado - ID inválido: %s %n", invalidId);
    }

    @Test
    @DisplayName("Deve deletar um usuário com sucesso")
    void shouldDeleteUserSuccessfully() {
        // Given
        UUID deleteId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(id, userName, password, rolesEntity);

        // When
        when(userRepository.findById(deleteId)).thenReturn(Optional.of(userEntity));
        doNothing().when(userRepository).delete(userEntity);
        Boolean isDeleted = userGateway.delete(deleteId);

        // Then
        verify(userRepository, times(1)).findById(deleteId);
        verify(userRepository, times(1)).delete(userEntity);
        assertEquals(true, isDeleted);

        System.out.printf("Usuário deletado com sucesso - Id: %s %n", id);
    }

    @Test
    @DisplayName("Deve buscar todos os usuários com sucesso")
    void shouldFindAllUsersSuccessfully() {
        // Given
        User userDomain1 = new User(UUID.randomUUID(), userName, password, rolesDomain);
        User userDomain2 = new User(UUID.randomUUID(), userName, password, rolesDomain);
        UserEntity userEntity1 = new UserEntity(userDomain1.getId(), userDomain1.getUserName(), userDomain1.getPassword(), rolesEntity);
        UserEntity userEntity2 = new UserEntity(userDomain2.getId(), userDomain2.getUserName(), userDomain2.getPassword(), rolesEntity);
        List<UserEntity> userEntities = List.of(userEntity1, userEntity2);

        // When
        mockedUserMapper.when(() -> UserMapper.toEntity(userDomain1)).thenReturn(userEntity1);
        mockedUserMapper.when(() -> UserMapper.toEntity(userDomain2)).thenReturn(userEntity2);
        mockedUserMapper.when(() -> UserMapper.toDomain(userEntity1)).thenReturn(userDomain1);
        mockedUserMapper.when(() -> UserMapper.toDomain(userEntity2)).thenReturn(userDomain2);
        when(userRepository.findAll()).thenReturn(userEntities);
        List<User> foundUsersDomain = userGateway.findAll();

        // Then
        verify(userRepository, times(1)).findAll();
        assertEquals(2, foundUsersDomain.size());
        assertEquals(userDomain1.getId(), foundUsersDomain.get(0).getId());
    }

    @Test
    @DisplayName("Deve lançar UserNotFoundException quando o usuário não for encontrado na atualização")
    void shouldThrowUserNotFoundExceptionWhenUserNotFoundOnUpdate() {
        // Given
        UUID nonExistentId = UUID.randomUUID();
        User userToUpdate = new User(nonExistentId, userName,password, rolesDomain);

        // When
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> {userGateway.update(userToUpdate);});

        // Then
        verify(userRepository, times(1)).findById(nonExistentId);
        assertEquals("User not found with id: " + nonExistentId, userNotFoundException.getMessage());
   }

    @Test
    @DisplayName("Deve lançar UserNotFoundException quando usuário não for encontrado na exclusão")
    void shouldThrowUserNotFoundExceptionWhenUserNotFoundOnDelete() {
        // Given
        UUID nonExistentId = UUID.randomUUID();

        // When
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Then
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> {
            userGateway.delete(nonExistentId);
        });
        verify(userRepository, times(1)).findById(nonExistentId);
        assertEquals("User not found with id: " + nonExistentId, userNotFoundException.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exception DataIntegrityViolationException quando teentar excluir usuário que está relacionado com um restaurante")
    void shouldThrowDataIntegrityViolationExceptionWhenDeletingUserWithRestaurantRelation() {
        // Given
        UUID userId = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(userId, userName, password, rolesEntity);

        // When
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        doThrow(new DataIntegrityViolationException("User is related to a restaurant")).when(userRepository).delete(userEntity);

        // Then
        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            userGateway.delete(userId);
        });
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(userEntity);
        assertEquals("Usuário é proprietário de um restaurante e não pose ser removido.", dataIntegrityViolationException.getMessage());

    }


}
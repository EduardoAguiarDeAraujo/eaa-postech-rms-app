package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.api.exception.RoleNotFoundException;
import br.eng.eaa.infra.db.entity.RoleEntity;
import br.eng.eaa.infra.db.mapper.RoleMapper;
import br.eng.eaa.infra.db.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

class RoleGatewayTest {

    private RoleRepository roleRepository;
    private MockedStatic<RoleMapper> mockedRoleMapper;
    private RoleGateway roleGateway;
    private UUID id;
    private String name;

    @BeforeEach
    void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        mockedRoleMapper = Mockito.mockStatic(RoleMapper.class);
        roleGateway = new RoleGateway(roleRepository);
        id = UUID.randomUUID();
        name = "Test Role";
    }

    @AfterEach
    void tearDown() {
        mockedRoleMapper.close();
    }

    @Test
    @DisplayName("Deve criar um role com sucesso")
    void shouldCreateRoleSuccessfully() {
        // Given
        Role role = new Role(id, name);
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        mockedRoleMapper.when(() -> RoleMapper.toRoleEntity(role)).thenReturn(roleEntity);
        mockedRoleMapper.when(() -> RoleMapper.toRoleDomain(roleEntity)).thenReturn(role);
        Mockito.when(roleRepository.save(roleEntity)).thenReturn(roleEntity);
        Role savedRole = roleGateway.save(role);

        // Then
        Mockito.verify(roleRepository, Mockito.times(1)).save(roleEntity);
        assertEquals(role.getId(), savedRole.getId());
        assertEquals(role.getName(), savedRole.getName());
    }

    @Test
    @DisplayName("Deve atualizar um role com sucesso")
    void shouldUpdateRoleSuccessfully() {
        // Given
        Role role = new Role(id, name);
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        mockedRoleMapper.when(() -> RoleMapper.toRoleEntity(role)).thenReturn(roleEntity);
        mockedRoleMapper.when(() -> RoleMapper.toRoleDomain(roleEntity)).thenReturn(role);
        Mockito.when(roleRepository.findById(roleEntity.getId())).thenReturn(Optional.of(roleEntity));
        Mockito.when(roleRepository.save(roleEntity)).thenReturn(roleEntity);
        Role updatedRole = roleGateway.update(role);

        // Then
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleEntity.getId());
        Mockito.verify(roleRepository, Mockito.times(1)).save(roleEntity);
        assertEquals(role.getId(), updatedRole.getId());
        assertEquals(role.getName(), updatedRole.getName());
    }

    @Test
    @DisplayName("Deve buscar um role por ID com sucesso")
    void shouldFindRoleByIdSuccessfully() {
        // Given
        Role role = new Role(id, name);
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        mockedRoleMapper.when(() -> RoleMapper.toRoleDomain(roleEntity)).thenReturn(role);
        Mockito.when(roleRepository.findById(id)).thenReturn(Optional.of(roleEntity));
        Role foundRole = roleGateway.findById(id);

        // Then
        Mockito.verify(roleRepository, Mockito.times(1)).findById(id);
        assertEquals(role.getId(), foundRole.getId());
        assertEquals(role.getName(), foundRole.getName());
    }

    @Test
    @DisplayName("Deve deletar um role por ID válido")
    void shouldDeleteRoleByIdSuccessfully() {
        // Given
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        Mockito.when(roleRepository.findById(id)).thenReturn(Optional.of(roleEntity));
        doNothing().when(roleRepository).delete(roleEntity);
        Boolean isDeleted = roleGateway.delete(id);

        // Then
        Mockito.verify(roleRepository, Mockito.times(1)).findById(id);
        Mockito.verify(roleRepository, Mockito.times(1)).delete(roleEntity);
        assertTrue(isDeleted);
    }

    @Test
    @DisplayName("Deve buscar uma lista todos os roles")
    void shouldFindAllRolesSuccessfully() {
        // Given
        Role role = new Role(id, name);
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        mockedRoleMapper.when(() -> RoleMapper.toRoleDomain(roleEntity)).thenReturn(role);
        Mockito.when(roleRepository.findAll()).thenReturn(List.of(roleEntity));
        List<Role> roles = roleGateway.findAll();

        // Then
        Mockito.verify(roleRepository, Mockito.times(1)).findAll();
        assertEquals(1, roles.size());
        assertEquals(role.getId(), roles.get(0).getId());
        assertEquals(role.getName(), roles.get(0).getName());
    }

    @Test
    @DisplayName("Deve lançar exceção RoleNotFoundException quando tentar atualizar role com id inexistente")
    void shouldThrowRoleNotFoundExceptionWhenUpdatingNonExistentRole() {
        // Given
        Role role = new Role(id, name);
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        mockedRoleMapper.when(() -> RoleMapper.toRoleEntity(role)).thenReturn(roleEntity);
        Mockito.when(roleRepository.findById(roleEntity.getId())).thenReturn(Optional.empty());

        // Then
        assertThrows(RoleNotFoundException.class, () -> roleGateway.update(role));
    }

    @Test
    @DisplayName("Deve lançar exceção RoleNotFoundException quando tentar excluir role por ID inexistente")
    void shouldThrowRoleNotFoundExceptionWhenDeletingNonExistentRole() {
        // Given
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        Mockito.when(roleRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(RoleNotFoundException.class, () -> roleGateway.delete(id));
    }

    @Test
    @DisplayName("Deve lançar exceção DataIntegrityViolationException quando tentar excluir role por ID existente")
    void shouldThrowDataIntegrityViolationExceptionWhenDeletingExistingRole() {
        // Given
        RoleEntity roleEntity = new RoleEntity(id, name);

        // When
        Mockito.when(roleRepository.findById(id)).thenReturn(Optional.of(roleEntity));
        Mockito.doThrow(new org.springframework.dao.DataIntegrityViolationException("")).when(roleRepository).delete(roleEntity);

        // Then
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> roleGateway.delete(id));
    }

}
package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IRoleGateway;
import br.eng.eaa.application.boundary.output.RoleOutputPort;
import br.eng.eaa.application.model.mapper.RoleDomainMapper;
import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleInteractorTest {

    private IRoleGateway roleGateway;
    private RoleOutputPort roleOutput;
    private RoleInteractor interactor;
    private MockedStatic<RoleDomainMapper> roleMapper;
    private UUID id;
    private String roleName;

    @BeforeEach
    void setUp() {
        this.roleGateway = Mockito.mock(IRoleGateway.class);
        this.roleOutput = Mockito.mock(RoleOutputPort.class);
        this.roleMapper = Mockito.mockStatic(RoleDomainMapper.class);

        this.interactor = new RoleInteractor(roleGateway, roleOutput);
        this.id = UUID.randomUUID();
        this.roleName = "ADMIN";
    }

    @AfterEach
    void tearDown() {
        roleMapper.close();
        roleGateway = null;
        roleOutput = null;
        interactor = null;
    }

    @Test
    @DisplayName("Deve salvar um role válido")
    void shouldSaveValidRole() {
        // Given
        RoleRequest roleRequest = new RoleRequest(roleName);
        Role role = new Role(id, roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);

        // When
        when(roleGateway.save(role)).thenReturn(role);
        when(roleOutput.execute(role)).thenReturn(roleResponse);
        roleMapper.when(() -> RoleDomainMapper.toRole(roleRequest)).thenReturn(role);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualResponse = interactor.save(roleRequest);

        // Then
        assertNotNull(actualResponse);
        assertEquals(roleResponse.getId(), actualResponse.getId());
        assertEquals(roleResponse.getName(), actualResponse.getName());
    }

    @Test
    @DisplayName("Deve atualizar um role válido")
    void shouldUpdateValidRole() {
        // Given
        RoleRequest roleRequest = new RoleRequest(roleName);
        Role role = new Role(id, roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);

        // When
        when(roleGateway.update(role)).thenReturn(role);
        when(roleOutput.execute(role)).thenReturn(roleResponse);
        roleMapper.when(() -> RoleDomainMapper.toRole(roleRequest)).thenReturn(role);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualResponse = interactor.update(roleRequest);

        // Then
        assertNotNull(actualResponse);
        assertEquals(roleResponse.getId(), actualResponse.getId());
        assertEquals(roleResponse.getName(), actualResponse.getName());
    }

    @Test
    @DisplayName("Deve retornar role por ID válido")
    void shouldReturnRoleById() {
        // Given
        Role role = new Role(id, roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);

        // When
        when(roleGateway.findById(id)).thenReturn(role);
        when(roleOutput.execute(role)).thenReturn(roleResponse);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualResponse = interactor.findById(id);

        // Then
        assertNotNull(actualResponse);
        assertEquals(roleResponse.getId(), actualResponse.getId());
        assertEquals(roleResponse.getName(), actualResponse.getName());
    }

    @Test
    @DisplayName("Deve retornar uma lista de roles válidos")
    void shouldReturnListOfValidRoles() {
        // Given
        Role role1 = new Role(id , "ADMIN");
        Role role2 = new Role(id, "CUSTOMER");
        RoleResponse roleResponse1 = new RoleResponse(id, roleName);
        RoleResponse roleResponse2 = new RoleResponse(id, "CUSTOMER");
        List<Role> roles = List.of(role1, role2);
        List<RoleResponse> roleResponses = List.of(roleResponse1, roleResponse2);

        // When
        when(roleGateway.findAll()).thenReturn(roles);
        when(roleOutput.execute(roles)).thenReturn(roleResponses);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role1)).thenReturn(roleResponse1);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role2)).thenReturn(roleResponse2);
        List<RoleResponse> actualResponses = interactor.findAll();

        // Then
        assertNotNull(actualResponses);
        assertEquals(2, actualResponses.size());
        assertEquals(roleResponses.get(0).getId(), actualResponses.get(0).getId());
        assertEquals(roleResponses.get(1).getId(), actualResponses.get(1).getId());
    }

    @Test
    @DisplayName("Deve deletar um role por ID válido")
    void shouldDeleteRoleById() {
        // Given
        boolean isDeleted = true;

        // When
        when(roleGateway.delete(id)).thenReturn(isDeleted);
        when(roleOutput.execute(isDeleted)).thenReturn(isDeleted);
        boolean actualResponse = interactor.delete(id);

        // Then
        assertTrue(actualResponse);
    }

}
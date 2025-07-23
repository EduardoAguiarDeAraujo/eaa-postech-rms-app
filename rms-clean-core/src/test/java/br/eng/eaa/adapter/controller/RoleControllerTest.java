package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.RoleGatewayStub;
import br.eng.eaa.application.boundary.output.RoleOutputPort;
import br.eng.eaa.application.model.mapper.RoleDomainMapper;
import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.application.usecase.RoleInteractor;
import br.eng.eaa.domain.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleControllerTest {

    private RoleOutputPort  roleOutputPort;
    private RoleGatewayStub  roleGateway;
    private RoleController roleController;
    private RoleInteractor roleInteractor;
    private MockedStatic<RoleDomainMapper> roleMapper;
    private UUID id;
    private String roleName;

    @BeforeEach
    void setUp() {
        this.roleGateway = Mockito.mock(RoleGatewayStub.class);
        this.roleOutputPort = Mockito.mock(RoleOutputPort.class);
        this.roleInteractor = Mockito.mock(RoleInteractor.class);
        this.roleMapper = Mockito.mockStatic(RoleDomainMapper.class);
        this.roleController = new RoleController(roleGateway);
        this.id = UUID.randomUUID();
        this.roleName = "ADMIN";
    }

    @AfterEach
    void tearDown() {
        roleMapper.close();
        roleGateway = null;
        roleOutputPort = null;
        roleInteractor = null;
        roleController = null;
    }

    @Test
    @DisplayName("Deve retornar um role válido ao salvar")
    void givenAValidRole_whenCallsSave_thenReturnSavedRole() {
        //GIVEN
        RoleRequest roleRequest = new RoleRequest(roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);
        Role role = new Role(id, roleName);

        //WHEN
        Mockito.when(roleGateway.save(role)).thenReturn(role);
        Mockito.when(roleOutputPort.execute(role)).thenReturn(roleResponse);
        Mockito.when(roleInteractor.save(roleRequest)).thenReturn(roleResponse);
        roleMapper.when(() -> RoleDomainMapper.toRole(roleRequest)).thenReturn(role);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualResponse = roleController.save(roleRequest);

        //THEN
        assertEquals(roleResponse.getId(), actualResponse.getId());
        assertEquals(roleResponse.getName(), actualResponse.getName());
    }

    @Test
    @DisplayName("Deve retornar um role válido ao atualizar")
    void givenAValidRoleRequest_whenCallsUpdate_thenReturnUpdatedRoleResponse() {
        //GIVEN
        RoleRequest roleRequest = new RoleRequest(id, roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);
        Role role = new Role(id, roleName);

        //WHEN
        Mockito.when(roleGateway.update(role)).thenReturn(role);
        Mockito.when(roleOutputPort.execute(role)).thenReturn(roleResponse);
        Mockito.when(roleInteractor.update(roleRequest)).thenReturn(roleResponse);
        roleMapper.when(() -> RoleDomainMapper.toRole(roleRequest)).thenReturn(role);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualResponse = roleController.update(roleRequest);

        //THEN
        assertEquals(roleResponse.getId(), actualResponse.getId());
        assertEquals(roleResponse.getName(), actualResponse.getName());
    }

    @Test
    @DisplayName("Deve retornar true ao excluir um role por ID válido")
    void givenAValidRoleRequest_whenCallsDelete_thenReturnDeletedRoleResponse() {
        //GIVEN
        UUID id = this.id;
        Boolean isDeleted = true;

        //WHEN
        Mockito.when(roleInteractor.delete(id)).thenReturn(isDeleted);
        Mockito.when(roleGateway.delete(id)).thenReturn(isDeleted);
        Mockito.when(roleOutputPort.execute(isDeleted)).thenReturn(isDeleted);
        Boolean actualResponse = roleController.delete(id);

        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar false ao excluir um role por ID inválido")
    void givenAInvalidRoleRequest_whenCallsDelete_thenReturnDeletedRoleResponse() {
        //GIVEN
        UUID id = UUID.randomUUID();
        Boolean isDeleted = false;

        //WHEN
        Mockito.when(roleInteractor.delete(id)).thenReturn(isDeleted);
        Mockito.when(roleGateway.delete(id)).thenReturn(isDeleted);
        Mockito.when(roleOutputPort.execute(isDeleted)).thenReturn(isDeleted);
        Boolean actualResponse = roleController.delete(id);

        //THEN
        assertEquals(isDeleted, actualResponse);
    }

    @Test
    @DisplayName("Deve retornar um role válido ao pesquisar por ID")
    void givenAValidRoleRequest_whenCallsFindById_thenReturnRoleResponse() {
        //GIVEN
        UUID id = this.id;
        RoleResponse roleResponse = new RoleResponse(id, roleName);
        Role role = new Role(id, roleName);

        //WHEN
        Mockito.when(roleGateway.findById(id)).thenReturn(role);
        Mockito.when(roleOutputPort.execute(role)).thenReturn(roleResponse);
        Mockito.when(roleInteractor.findById(id)).thenReturn(roleResponse);
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualResponse = roleController.findById(id);

        //THEN
        assertEquals(roleResponse.getId(), actualResponse.getId());
        assertEquals(roleResponse.getName(), actualResponse.getName());
    }

    @Test
    @DisplayName("Deve retornar uma lista de roles válidos ao pesquisar todos")
    void givenAValidRoleRequest_whenCallsFindAll_thenReturnListOfRoleResponse() {
        //GIVEN
        Role role = new Role(id, roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);

        Mockito.when(roleGateway.findAll()).thenReturn(List.of(role));
        Mockito.when(roleOutputPort.execute(List.of(role))).thenReturn(List.of(roleResponse));
        Mockito.when(roleInteractor.findAll()).thenReturn(List.of(roleResponse));
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);

        //WHEN
        List<RoleResponse> actualResponse = roleController.findAll();

        //THEN
        assertEquals(1, actualResponse.size());
        assertEquals(roleResponse.getId(), actualResponse.get(0).getId());
        assertEquals(roleResponse.getName(), actualResponse.get(0).getName());
    }


}
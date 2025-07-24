package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.model.mapper.RoleDomainMapper;
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

class RolePresenterTest {

    private UUID id;
    private String roleName;
    private RolePresenter rolePresenter;
    private MockedStatic<RoleDomainMapper> roleMapper;


    @BeforeEach
    void setUp() {
        this.id = UUID.randomUUID();
        this.roleName = "ADMIN";
        this.roleMapper = Mockito.mockStatic(RoleDomainMapper.class);
        this.rolePresenter = new RolePresenter();
    }

    @AfterEach
    void tearDown() {
        roleMapper.close();
        id = null;
        roleName = null;
        rolePresenter = null;
    }

    @Test
    @DisplayName("Deve executar o RolePresenter corretamente")
    void shouldExecuteRolePresenter() {
        // Given
        Role role = new Role(id, roleName);
        RoleResponse roleResponse = new RoleResponse(id, roleName);

        // When
        roleMapper.when(() -> RoleDomainMapper.toResponse(role)).thenReturn(roleResponse);
        RoleResponse actualRoleResponse = rolePresenter.execute(role);

        // Then
        assertNotNull(actualRoleResponse);
        assertEquals(roleResponse.getId(), actualRoleResponse.getId());
        assertEquals(roleResponse.getName(), actualRoleResponse.getName());
    }

    @Test
    @DisplayName("Deve executar o RolePresenter com uma lista de roles")
    void shouldExecuteRolePresenterWithList() {
        // Given
        Role role1 = new Role(id, "ADMIN");
        Role role2 = new Role(id, "OWNER");
        RoleResponse roleResponse1 = new RoleResponse(id, "ADMIN");
        RoleResponse roleResponse2 = new RoleResponse(id, "OWNER");
        List<Role> roles = List.of(role1, role2);
        List<RoleResponse> roleResponses = List.of(roleResponse1, roleResponse2);

        // When
        roleMapper.when(() -> RoleDomainMapper.toResponse(role1)).thenReturn(new RoleResponse(role1.getId(), role1.getName()));
        roleMapper.when(() -> RoleDomainMapper.toResponse(role2)).thenReturn(new RoleResponse(role2.getId(), role2.getName()));
        List<RoleResponse> actualRoleResponses = rolePresenter.execute(roles);

        // Then
        assertNotNull(actualRoleResponses);
        assertEquals(roleResponses.size(), actualRoleResponses.size());
        assertEquals(roleResponses.get(0).getId(), actualRoleResponses.get(0).getId());
        assertEquals(roleResponses.get(1).getId(), actualRoleResponses.get(1).getId());
    }

    @Test
    @DisplayName("Deve executar o RolePresenter com boolean")
    void shouldExecuteRolePresenterWithBoolean() {
        // Given
        Boolean isDeleted = true;

        // When
        Boolean actualResponse = rolePresenter.execute(isDeleted);

        // Then
        assertNotNull(actualResponse);
        assertEquals(isDeleted, actualResponse);
    }

}
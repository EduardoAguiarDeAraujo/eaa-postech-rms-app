package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.RoleController;
import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.infra.db.dto.request.RoleRequestDto;
import br.eng.eaa.infra.db.dto.response.RoleResponseDto;
import br.eng.eaa.infra.db.mapper.RoleMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RoleServiceTest {

    private RoleController roleController;
    private MockedStatic<RoleMapper> mockRoleMapper;
    private RoleService roleService;
    private UUID id;
    private String name;

    @BeforeEach
    void setUp() {
        roleController = Mockito.mock(RoleController.class);
        mockRoleMapper = Mockito.mockStatic(RoleMapper.class);
        roleService = new RoleService(roleController);
        id = UUID.randomUUID();
        name = "ADMIN";
    }

    @AfterEach
    void tearDown() {
        mockRoleMapper.close();
    }

    @Test
    @DisplayName("Deve criar uma role com sucesso")
    void shouldSaveSuccessfully() {
        // Given
        RoleRequest roleRequest = new RoleRequest(id, name);
        RoleResponse roleResponse = new RoleResponse(id, name);
        RoleRequestDto roleRequestDto = new RoleRequestDto(id, name);
        RoleResponseDto roleResponseDto = new RoleResponseDto(id, name);

        // When
        mockRoleMapper.when(() -> RoleMapper.toRequest(roleRequestDto)).thenReturn(roleRequest);
        mockRoleMapper.when(() -> RoleMapper.toDto(roleResponse)).thenReturn(roleResponseDto);
        Mockito.when(roleController.save(roleRequest)).thenReturn(roleResponse);
        RoleResponseDto savedRoleResponseDto = roleService.save(roleRequestDto);

        // Then
        mockRoleMapper.verify(() -> RoleMapper.toRequest(roleRequestDto), times(1));
        mockRoleMapper.verify(() -> RoleMapper.toDto(roleResponse), times(1));
        verify(roleController, times(1)).save(roleRequest);
        assertEquals(roleResponseDto.id(), savedRoleResponseDto.id());
        assertEquals(roleResponseDto.name(), savedRoleResponseDto.name());
    }

    @Test
    @DisplayName("Deve buscar uma role por ID com sucesso")
    void shouldFindByIdSuccessfully() {
        // Given
        RoleResponse roleResponse = new RoleResponse(id, name);
        RoleResponseDto roleResponseDto = new RoleResponseDto(id, name);

        // When
        mockRoleMapper.when(() -> RoleMapper.toDto(roleResponse)).thenReturn(roleResponseDto);
        Mockito.when(roleController.findById(id)).thenReturn(roleResponse);
        RoleResponseDto foundRoleResponseDto = roleService.findById(id);

        // Then
        mockRoleMapper.verify(() -> RoleMapper.toDto(roleResponse), times(1));
        verify(roleController, times(1)).findById(id);
        assertEquals(roleResponseDto.id(), foundRoleResponseDto.id());
        assertEquals(roleResponseDto.name(), foundRoleResponseDto.name());
    }

    @Test
    @DisplayName("Deve atualizar uma role com sucesso")
    void shouldUpdateSuccessfully() {
        // Given
        RoleRequest roleRequest = new RoleRequest(id, name);
        RoleResponse roleResponse = new RoleResponse(id, name);
        RoleRequestDto roleRequestDto = new RoleRequestDto(id, name);
        RoleResponseDto roleResponseDto = new RoleResponseDto(id, name);

        // When
        mockRoleMapper.when(() -> RoleMapper.toRequest(roleRequestDto)).thenReturn(roleRequest);
        mockRoleMapper.when(() -> RoleMapper.toDto(roleResponse)).thenReturn(roleResponseDto);
        Mockito.when(roleController.update(roleRequest)).thenReturn(roleResponse);
        RoleResponseDto updatedRoleResponseDto = roleService.update(roleRequestDto);

        // Then
        mockRoleMapper.verify(() -> RoleMapper.toRequest(roleRequestDto), times(1));
        mockRoleMapper.verify(() -> RoleMapper.toDto(roleResponse), times(1));
        verify(roleController, times(1)).update(roleRequest);
        assertEquals(roleResponseDto.id(), updatedRoleResponseDto.id());
        assertEquals(roleResponseDto.name(), updatedRoleResponseDto.name());
    }

    @Test
    @DisplayName("Deve deletar uma role com sucesso")
    void shouldDeleteSuccessfully() {
        // Given
        Boolean isDeleted = true;

        Mockito.when(roleController.delete(id)).thenReturn(isDeleted);
        Boolean adtualDeleted = roleService.delete(id);

        // Then
        verify(roleController, times(1)).delete(id);
        assertEquals(isDeleted, adtualDeleted);
    }

    @Test
    @DisplayName("Deve retornar false ao tentar deletar uma role inexistente")
    void shouldReturnFalseWhenDeletingNonExistentRole() {
        // Given
        Boolean isDeleted = false;

        // When
        Mockito.when(roleController.delete(id)).thenReturn(isDeleted);
        Boolean actualDeleted = roleService.delete(id);

        // Then
        verify(roleController, times(1)).delete(id);
        assertEquals(actualDeleted, isDeleted);
    }

    @Test
    @DisplayName("Deve buscar todas as roles com sucesso")
    void shouldFindAllRolesSuccessfully() {
        // Given
        List<RoleResponse> rolesResponse = List.of(new RoleResponse(id, name),new RoleResponse(id, name));
        RoleResponseDto roleResponseDto = new RoleResponseDto(id, name);

        // When
        mockRoleMapper.when(() -> RoleMapper.toDto(any(RoleResponse.class))).thenReturn(roleResponseDto);
        Mockito.when(roleController.findAll()).thenReturn(rolesResponse);
        List<RoleResponseDto> foundRoles = roleService.findAll();

        // Then
        verify(roleController, times(1)).findAll();
        assertEquals(rolesResponse.size(), foundRoles.size());
        assertEquals(roleResponseDto.id(), foundRoles.get(0).id());
    }



}
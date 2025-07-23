package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.RoleController;
import br.eng.eaa.application.model.request.RoleRequest;
import br.eng.eaa.application.model.response.RoleResponse;
import br.eng.eaa.infra.db.dto.request.RoleRequestDto;
import br.eng.eaa.infra.db.dto.response.RoleResponseDto;
import br.eng.eaa.infra.db.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleController roleController;

    public RoleService(RoleController roleController) {
        this.roleController = roleController;
    }

    public RoleResponseDto save(RoleRequestDto roleDto) {
        RoleRequest roleRequest = RoleMapper.toRequest(roleDto);
        RoleResponse createdRole = roleController.save(roleRequest);
        return RoleMapper.toDto(createdRole);
    }

    public RoleResponseDto findById(UUID id) {
        RoleResponse roleResponse = roleController.findById(id);
        return RoleMapper.toDto(roleResponse);
    }

    public RoleResponseDto update(RoleRequestDto roleDto) {
        RoleRequest roleRequest = RoleMapper.toRequest(roleDto);
        RoleResponse updatedRole = roleController.update(roleRequest);
        return RoleMapper.toDto(updatedRole);
    }

    public Boolean delete(UUID id) {
        return roleController.delete(id);
    }

    public List<RoleResponseDto> findAll() {
        List<RoleResponse> roles = roleController.findAll();
        return roles.stream().map(RoleMapper::toDto).toList();
    }
}

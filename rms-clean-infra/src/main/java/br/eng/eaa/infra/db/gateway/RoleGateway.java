package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.adapter.gateway.IRoleGateway;
import br.eng.eaa.domain.entity.Role;
import br.eng.eaa.infra.api.exception.RoleNotFoundException;
import br.eng.eaa.infra.db.entity.RoleEntity;
import br.eng.eaa.infra.db.mapper.RoleMapper;
import br.eng.eaa.infra.db.repository.RoleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RoleGateway implements IRoleGateway {

    private final RoleRepository roleRepository;

    public RoleGateway(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = RoleMapper.toRoleEntity(role);
        RoleEntity savedRoleEntity = roleRepository.save(roleEntity);
        return RoleMapper.toRoleDomain(savedRoleEntity);
    }

    @Override
    public Role update(Role role) {
        RoleEntity roleEntity = roleRepository.findById(role.getId()).orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + role.getId()));
        roleEntity.setName(role.getName());
        RoleEntity updatedRoleEntity = roleRepository.save(roleEntity);
        return RoleMapper.toRoleDomain(updatedRoleEntity);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).map(RoleMapper::toRoleDomain).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream().map(RoleMapper::toRoleDomain).toList();
    }

    @Override
    public Boolean delete(UUID id) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + id));
        try {
            roleRepository.delete(roleEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Cannot delete role with id: " + id + " due to data integrity violation");
        }
        return true;
    }
}

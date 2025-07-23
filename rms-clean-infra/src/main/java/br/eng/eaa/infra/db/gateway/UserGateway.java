package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.adapter.gateway.IUserGateway;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.infra.api.exception.UserNotFoundException;
import br.eng.eaa.infra.db.entity.UserEntity;
import br.eng.eaa.infra.db.mapper.RoleMapper;
import br.eng.eaa.infra.db.mapper.UserMapper;
import br.eng.eaa.infra.db.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserGateway implements IUserGateway {

    private final UserRepository userRepository;

    protected UserGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.toDomain(savedUserEntity);
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getId()));
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles().stream().map(RoleMapper::toRoleEntity).toList());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return UserMapper.toDomain(savedUserEntity);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).map(UserMapper::toDomain).orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    @Override
    public Boolean delete(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        try {
            userRepository.delete(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Usuário é proprietário de um restaurante e não pose ser removido.");
        }
        return true;
    }
}

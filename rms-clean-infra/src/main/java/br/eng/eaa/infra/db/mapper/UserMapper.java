package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.infra.db.dto.request.UserRequestDto;
import br.eng.eaa.infra.db.dto.response.UserResponseDto;
import br.eng.eaa.infra.db.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("UserEntity must be not null");
        }
        if (userEntity.getPassword() == null) {
            return new User(
                userEntity.getId(),
                userEntity.getUserName()
            );
        }
        return new User(
            userEntity.getId(),
            userEntity.getUserName(),
            userEntity.getPassword(),
            userEntity.getRoles().stream().map(RoleMapper::toRoleDomain).toList()
        );
    }

    public static UserEntity toEntity(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must be not null");
        }
        if (user.getPassword() == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(user.getId());
            userEntity.setUserName(user.getUserName());
            return userEntity;
        }

        return new UserEntity(
            user.getId(),
            user.getUserName(),
            user.getPassword(),
            user.getRoles().stream().map(RoleMapper::toRoleEntity).toList()
        );
    }

    public static UserRequest toRequest(UserRequestDto userRequestDto) {
        if (userRequestDto == null) {
            throw new IllegalArgumentException("UserRequestDto must be not null");
        }
        return new UserRequest(
            userRequestDto.id(),
            userRequestDto.userName(),
            userRequestDto.password(),
            userRequestDto.roles().stream().map(RoleMapper::toRoleDomain).toList()
        );
    }

    public static UserResponseDto toDto(UserResponse userResponse) {
        if (userResponse == null) {
            throw new IllegalArgumentException("UserResponse must be not null");
        }
        return new UserResponseDto(
            userResponse.getId(),
            userResponse.getUserName(),
            userResponse.getRoles().stream().map(RoleMapper::toRoleEntity).toList()
        );
    }

    public static UserResponseDto toDto(User user) {
        if (user == null) {
            throw new IllegalArgumentException("UserResponse must be not null");
        }
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getRoles().stream().map(RoleMapper::toRoleEntity).toList()
        );
    }


}

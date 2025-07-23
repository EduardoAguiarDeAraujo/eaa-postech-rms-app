package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.UserController;
import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.infra.db.dto.request.UserRequestDto;
import br.eng.eaa.infra.db.dto.response.UserResponseDto;
import br.eng.eaa.infra.db.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserController userController;

    public UserService(UserController userController) {
        this.userController = userController;
    }

    public UserResponseDto save(UserRequestDto userDto) {
        UserRequest userRequest = UserMapper.toRequest(userDto);
        UserResponse createdUser = userController.save(userRequest);
        return UserMapper.toDto(createdUser);
    }

    public UserResponseDto findById(UUID id) {
        UserResponse userResponse = userController.findById(id);
        return UserMapper.toDto(userResponse);
    }

    public UserResponseDto update(UserRequestDto userDto) {
        UserRequest userRequest = UserMapper.toRequest(userDto);
        UserResponse updatedUser = userController.update(userRequest);
        return UserMapper.toDto(updatedUser);
    }

    public Boolean delete(UUID id) {
        return userController.delete(id);
    }

    public List<UserResponseDto> findAll() {
        List<UserResponse> users = userController.findAll();
        return users.stream().map(UserMapper::toDto).toList();
    }
}

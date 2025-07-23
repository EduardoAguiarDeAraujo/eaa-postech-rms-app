package br.eng.eaa.application.boundary.input;

import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserInputPort {
    UserResponse save(UserRequest userRequest);
    UserResponse update(UserRequest userRequest);
    UserResponse findById(UUID id);
    List<UserResponse> findAll();
    Boolean delete(UUID id);

}

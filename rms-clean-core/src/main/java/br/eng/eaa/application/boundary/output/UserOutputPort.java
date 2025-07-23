package br.eng.eaa.application.boundary.output;

import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;

import java.util.List;

public interface UserOutputPort {
    UserResponse execute(User user);
    List<UserResponse> execute(List<User> user);
    Boolean execute(Boolean isDeleted);
}

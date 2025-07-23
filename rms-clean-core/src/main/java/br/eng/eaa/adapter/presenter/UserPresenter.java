package br.eng.eaa.adapter.presenter;

import br.eng.eaa.application.boundary.output.UserOutputPort;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;

import java.util.List;

public class UserPresenter implements UserOutputPort {

    @Override
    public UserResponse execute(User user) {
        return toResponse(user);
    }

    @Override
    public List<UserResponse> execute(List<User> user) {
        return user.stream().map(UserPresenter::toResponse).toList();
    }

    @Override
    public Boolean execute(Boolean isDeleted) {
        return isDeleted;
    }

    private static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getUserName(), user.getRoles());
    }
}

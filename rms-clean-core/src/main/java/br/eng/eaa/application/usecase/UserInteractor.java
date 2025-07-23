package br.eng.eaa.application.usecase;

import br.eng.eaa.adapter.gateway.IUserGateway;
import br.eng.eaa.application.boundary.input.UserInputPort;
import br.eng.eaa.application.boundary.output.UserOutputPort;
import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public class UserInteractor implements UserInputPort {

    private IUserGateway userGateway;
    private UserOutputPort userOutput;
    private List<Role> roles;


    public UserInteractor(IUserGateway userGateway, UserOutputPort userOutput) {
        this.userGateway = userGateway;
        this.userOutput = userOutput;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = toUser(userRequest);
        User userSaved = userGateway.save(user);
        return userOutput.execute(userSaved);
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        User user = toUser(userRequest);
        User userSaved = userGateway.update(user);
        return userOutput.execute(userSaved);
    }

    @Override
    public UserResponse findById(UUID id) {
        User user = userGateway.findById(id);
        return userOutput.execute(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userGateway.findAll();
        return userOutput.execute(users);
    }

    @Override
    public Boolean delete(UUID id) {
        Boolean isDeleted = userGateway.delete(id);
        return userOutput.execute(isDeleted);
    }

    private User toUser(UserRequest userRequest){
        if (userRequest.getId() != null){
            if (userRequest.getPassword() != null) {
                return new User(userRequest.getId(), userRequest.getUserName(), userRequest.getPassword(), userRequest.getRoles());
            }
            return new User(userRequest.getId(), userRequest.getUserName(),  userRequest.getRoles());
        }
        return new User(userRequest.getUserName(),  userRequest.getPassword(), userRequest.getRoles());
    }
}

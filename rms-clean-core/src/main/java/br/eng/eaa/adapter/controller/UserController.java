package br.eng.eaa.adapter.controller;

import br.eng.eaa.adapter.gateway.IUserGateway;
import br.eng.eaa.adapter.presenter.UserPresenter;
import br.eng.eaa.application.boundary.input.UserInputPort;
import br.eng.eaa.application.boundary.output.UserOutputPort;
import br.eng.eaa.application.model.request.UserRequest;
import br.eng.eaa.application.model.response.UserResponse;
import br.eng.eaa.application.usecase.UserInteractor;

import java.util.List;
import java.util.UUID;

public class UserController {

    private UserInputPort userInputPort;

    public UserController(IUserGateway userGateway) {
        UserOutputPort userOutputPort = new UserPresenter();
        this.userInputPort = new UserInteractor(userGateway, userOutputPort);
    }

    public UserResponse save(UserRequest userRequest) {
        return userInputPort.save(userRequest);
    }

    public UserResponse update(UserRequest userRequest) {
        return userInputPort.update(userRequest);
    }

    public UserResponse findById(UUID id) {
        return userInputPort.findById(id);
    }

    public List<UserResponse> findAll(){
        return userInputPort.findAll();
    }

    public Boolean delete(UUID id) {
        return userInputPort.delete(id);
    }

}

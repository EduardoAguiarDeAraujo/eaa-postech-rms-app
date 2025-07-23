package br.eng.eaa.application.model.response;

import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public class UserResponse {

    private UUID id;
    private String  userName;
    private List<Role> roles;

    public UserResponse(UUID id, String userName, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

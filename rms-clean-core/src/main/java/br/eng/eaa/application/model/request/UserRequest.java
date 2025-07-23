package br.eng.eaa.application.model.request;

import br.eng.eaa.domain.entity.Role;

import java.util.List;
import java.util.UUID;

public class UserRequest {
    private UUID id;
    private String  userName;
    private String password;
    private List<Role> roles;

    public UserRequest(String userName, String password, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public UserRequest(UUID id, String userName, String password, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

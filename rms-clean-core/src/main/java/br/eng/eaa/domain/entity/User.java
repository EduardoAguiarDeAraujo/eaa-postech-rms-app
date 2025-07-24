package br.eng.eaa.domain.entity;

import br.eng.eaa.domain.valueobject.Password;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String  userName;
    private Password password;
    private List<Role> roles;

    public User(String userName, String password) {
        setId(UUID.randomUUID());
        setUserName(userName);
        setPassword(password);
    }

    public User(UUID id, String userName) {
        setId(id);
        setUserName(userName);
    }

    public User(String userName, String password, List<Role> roles) {
        setId(UUID.randomUUID());
        setUserName(userName);
        setPassword(password);
        setRoles(roles);
    }

    public User(UUID id, String userName, String password, List<Role> roles) {
        setId(id);
        setUserName(userName);
        setPassword(password);
        setRoles(roles);
    }

    public User(UUID id, String userName, List<Role> roles) {
        setId(id);
        setUserName(userName);
        setRoles(roles);
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        if (password == null) {
            return null;
        }
        return password.getValue();
    }

    public List<Role> getRoles() {
        return roles;
    }

    private void setUserName(String userName) {
        validateString(userName, "username");
        this.userName = userName;
    }

    private void setId(UUID id) {
        if ( id == null) {
            throw new IllegalArgumentException("User id, name and roles must be not null");
        }
        this.id = id;
    }

    private void setPassword(String password) {
        this.password = new Password(password);
    }

    private void setRoles(List<Role> roles) {
        if (roles == null) {
            throw new IllegalArgumentException("User id, name and roles must be not null");
        }
        this.roles = new ArrayList<>();
        this.roles.clear();
        this.roles.addAll(roles);
    }

    private void validateString(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " name cannot be null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " name cannot be empty");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " name cannot be blank");
        }
    }
}

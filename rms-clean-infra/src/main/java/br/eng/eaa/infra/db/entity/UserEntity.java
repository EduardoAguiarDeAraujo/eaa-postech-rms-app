package br.eng.eaa.infra.db.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class UserEntity {

    @Id
    private UUID id;
    private String  userName;
    private String password;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(UUID id, String userName) {
        setId(id);
        setUserName(userName);
    }
    public UserEntity(UUID id, String userName, String password, List<RoleEntity> roles) {
        setId(id);
        setUserName(userName);
        setPassword(password);
        setRoles(roles);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        validateString(userName, "Username");
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validateString(password, "Password");
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        if (roles == null) {
            throw new IllegalArgumentException("Roles cannot be null");
        }
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

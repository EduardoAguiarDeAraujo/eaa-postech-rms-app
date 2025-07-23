package br.eng.eaa.application.model.request;

import java.util.UUID;

public class RoleRequest {

    private UUID id;
    private String name;

    public RoleRequest(String name) {
        setName(name);
    }

    public RoleRequest(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

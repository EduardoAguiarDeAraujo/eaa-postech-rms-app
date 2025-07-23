package br.eng.eaa.application.model.response;

import java.util.UUID;

public class RoleResponse {

    private UUID id;
    private String name;

    public RoleResponse(UUID id, String name) {
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

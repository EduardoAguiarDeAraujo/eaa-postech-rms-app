package br.eng.eaa.domain.entity;

import java.util.UUID;

public class Role {

    private UUID id;
    private String name;

    public Role(String name) {
        this(UUID.randomUUID(), name);
    }

    public Role(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Role name must be not null");
        }
        this.name = name;
    }

    public void setId(UUID id) {
        if (id == null) {
            id = UUID.randomUUID();
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

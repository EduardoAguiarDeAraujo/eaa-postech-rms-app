package br.eng.eaa.infra.db.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Hidden
@Table(name = "tb_role")
public class RoleEntity {

    @Id
    private UUID id;
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(UUID id, String name) {
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

    @Override
    public String toString() {
        return this.name;
    }
}

package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.entity.Role;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserGatewayStub implements IUserGateway{

    private UUID id;
    private String name;
    private List<Role> roles;

    public UserGatewayStub() {
        this.id = UUID.fromString("31d21f36-6edc-4f3d-9fda-879e09c739fe");
        this.name = "Eduardo";
        this.roles = Arrays.asList(new Role(UUID.randomUUID(), "ADMIN"),new Role(UUID.randomUUID(), "OWNER"));
    }

    @Override
    public User save(User user) {
        return new User(id, name, roles);
    }

    @Override
    public User update(User user) {
        return new User(id, name, roles) ;
    }

    @Override
    public User findById(UUID id) {
        if(id.equals(this.id)){
            return new User(id, name, roles) ;
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of(
                new User(id, name, roles),
                new User(id, name, roles)
        );
    }

    @Override
    public Boolean delete(UUID id) {
        if(id.equals(this.id)){
            return true;
        }
        return false;
    }
}

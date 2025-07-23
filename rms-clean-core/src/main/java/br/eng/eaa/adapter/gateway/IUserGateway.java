package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserGateway {
    User save(User user);
    User update(User user);
    User findById(UUID id);
    List<User> findAll();
    Boolean delete(UUID id);
}

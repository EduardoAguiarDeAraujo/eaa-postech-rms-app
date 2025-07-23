package br.eng.eaa.adapter.gateway;

import br.eng.eaa.domain.entity.Menu;

import java.util.List;
import java.util.UUID;

public interface IMenuGateway {
    Menu save(Menu menu);
    Menu update(Menu menu);
    Menu findById(UUID id);
    List<Menu> findAll();
    Boolean delete(UUID id);
}

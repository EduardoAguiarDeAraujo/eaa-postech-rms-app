package br.eng.eaa.infra.db.repository;

import br.eng.eaa.infra.db.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository <MenuEntity, UUID> {

    UUID id(UUID id);
}

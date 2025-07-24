package br.eng.eaa.infra.db.gateway;

import br.eng.eaa.adapter.gateway.IMenuGateway;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.infra.api.exception.MenuNotFoundException;
import br.eng.eaa.infra.api.exception.RestaurantNotFoundException;
import br.eng.eaa.infra.db.entity.MenuEntity;
import br.eng.eaa.infra.db.entity.RestaurantEntity;
import br.eng.eaa.infra.db.mapper.MenuMapper;
import br.eng.eaa.infra.db.repository.MenuRepository;
import br.eng.eaa.infra.db.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MenuGateway implements IMenuGateway {

    public static final String MENU_NOT_FOUND_WITH_ID = "Menu not found with id: ";
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuGateway(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Menu save(Menu menu) {
        MenuEntity menuEntity = MenuMapper.toEntity(menu);
        RestaurantEntity restaurantEntity = restaurantRepository.findById(menu.getRestaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + menu.getRestaurantId()));
        menuEntity.setRestaurant(restaurantEntity);
        MenuEntity savedMenuEntity = menuRepository.save(menuEntity);
        return MenuMapper.toDomain(savedMenuEntity);
    }

    @Override
    public Menu update(Menu menu) {
        MenuEntity menuEntity = menuRepository.findById(menu.getId()).orElseThrow(() -> new MenuNotFoundException(MENU_NOT_FOUND_WITH_ID + menu.getId()));
        RestaurantEntity restaurantEntity = restaurantRepository.findById(menu.getRestaurantId()).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + menu.getRestaurantId()));
        menuEntity.setName(menu.getName());
        menuEntity.setDescription(menu.getDescription());
        menuEntity.setPrice(menu.getPrice());
        menuEntity.setAvailable(menu.getAvailable());
        menuEntity.setImageUrl(menu.getImageUrl());
        menuEntity.setRestaurant(restaurantEntity);
        MenuEntity savedMenuEntity = menuRepository.save(menuEntity);
        return MenuMapper.toDomain(savedMenuEntity);
    }

    @Override
    public Menu findById(UUID id) {
        return menuRepository.findById(id).map(MenuMapper::toDomain).orElseThrow(() -> new MenuNotFoundException(MENU_NOT_FOUND_WITH_ID + id));
    }

    @Override
    public List<Menu> findAll() {
        List<MenuEntity> menuEntities = menuRepository.findAll();
        return menuEntities.stream().map(MenuMapper::toDomain).toList();
    }

    @Override
    public Boolean delete(UUID id) {
        MenuEntity menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(MENU_NOT_FOUND_WITH_ID + id));
        menuRepository.delete(menuEntity);
        return true;
    }

}

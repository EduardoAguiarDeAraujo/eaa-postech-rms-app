package br.eng.eaa.infra.db.mapper;

import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.infra.db.dto.request.MenuRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.db.entity.MenuEntity;

public class MenuMapper {

    public static MenuEntity toEntity(Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("Menu must be not null");
        }
        return new MenuEntity(
            menu.getId(),
            menu.getName(),
            menu.getDescription(),
            menu.getPrice(),
            menu.getAvailable(),
            menu.getImageUrl()
        );
    }

    public static Menu toDomain(MenuEntity menuEntity) {
        if (menuEntity == null) {
            throw new IllegalArgumentException("MenuEntity must be not null");
        }
        return new Menu(
            menuEntity.getId(),
            menuEntity.getName(),
            menuEntity.getDescription(),
            menuEntity.getPrice(),
            menuEntity.getAvailable(),
            menuEntity.getImageUrl(),
            menuEntity.getRestaurant().getId()
        );
    }

    public static MenuRequest toRequest(MenuRequestDto menuRequestDto){
        if (menuRequestDto == null) {
            throw new IllegalArgumentException("MenuRequestDto must be not null");
        }
        if (menuRequestDto.id() == null) {
            return new MenuRequest(
                menuRequestDto.name(),
                menuRequestDto.description(),
                menuRequestDto.price(),
                menuRequestDto.available(),
                menuRequestDto.imageUrl(),
                menuRequestDto.restaurantId()
            );
        }
        return new MenuRequest(
            menuRequestDto.id(),
            menuRequestDto.name(),
            menuRequestDto.description(),
            menuRequestDto.price(),
            menuRequestDto.available(),
            menuRequestDto.imageUrl(),
            menuRequestDto.restaurantId()
        );
    }

    public static MenuResponseDto toDto(MenuResponse menuResponse){
        if (menuResponse == null) {
            throw new IllegalArgumentException("MenuResponse must be not null");
        }
        return new MenuResponseDto(
                menuResponse.getId(),
                menuResponse.getName(),
                menuResponse.getDescription(),
                menuResponse.getPrice(),
                menuResponse.getAvailable(),
                menuResponse.getImageUrl(),
                menuResponse.getRestaurant()
        );
    }

    public static MenuResponseDto fromDomainToDto(Menu menu){
        if (menu == null) {
            throw new IllegalArgumentException("MenuResponse must be not null");
        }
        return new MenuResponseDto(
                menu.getId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getAvailable(),
                menu.getImageUrl(),
                menu.getRestaurantId()
        );
    }

}

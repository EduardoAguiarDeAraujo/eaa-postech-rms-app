package br.eng.eaa.infra.db.dto.request;

import br.eng.eaa.infra.db.entity.AddressEntity;
import br.eng.eaa.infra.db.entity.CuisineTypeEnum;
import br.eng.eaa.infra.db.entity.MenuEntity;
import br.eng.eaa.infra.db.entity.UserEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record RestaurantRequestDto (
        UUID id,
        String name,
        CuisineTypeEnum cuisineType,
        LocalTime openTime,
        LocalTime closeTime,
        UserEntity owner,
        AddressEntity address,
        List<MenuEntity> menus
) {
    public RestaurantRequestDto {
        if (menus == null) {
            menus = new ArrayList<>();
        }
    }
}

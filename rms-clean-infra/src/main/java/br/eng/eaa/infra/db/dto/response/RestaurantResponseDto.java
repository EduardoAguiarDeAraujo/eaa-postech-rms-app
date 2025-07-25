package br.eng.eaa.infra.db.dto.response;

import br.eng.eaa.infra.db.entity.AddressEntity;
import br.eng.eaa.infra.db.entity.CuisineTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Schema
public record RestaurantResponseDto(
        UUID id,
        String name,
        CuisineTypeEnum cuisineType,
        LocalTime openTime,
        LocalTime closeTime,
        UserResponseDto owner,
        AddressEntity address,
        List<MenuResponseDto> menus
) {}

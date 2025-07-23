package br.eng.eaa.application.model.request;

import br.eng.eaa.domain.entity.Menu;
import br.eng.eaa.domain.entity.User;
import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class RestaurantRequest {
    private UUID id;
    private String name;
    private CuisineType cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private User owner;
    private Address address;
    private List<Menu> menus;

    public RestaurantRequest(String name, CuisineType cuisineType, LocalTime openTime, LocalTime closeTime, User owner, Address address, List<Menu> menus) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
        this.address = address;
        this.menus = menus;
    }

    public RestaurantRequest(UUID id, String name, CuisineType cuisineType, LocalTime openTime, LocalTime closeTime, User owner, Address address, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.cuisineType = cuisineType;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
        this.address = address;
        this.menus = menus;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public User getOwner() {
        return owner;
    }

    public Address getAddress() {
        return address;
    }

    public List<Menu> getMenus() {
        return menus;
    }


}

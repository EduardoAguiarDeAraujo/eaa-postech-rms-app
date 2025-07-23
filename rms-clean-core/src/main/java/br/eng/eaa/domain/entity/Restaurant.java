package br.eng.eaa.domain.entity;

import br.eng.eaa.domain.valueobject.Address;
import br.eng.eaa.domain.valueobject.CuisineType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String name;
    private CuisineType cuisineType;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Boolean isOpen;
    private User owner;
    private Address address;
    private List<Menu> menus = new ArrayList<>();

    public Restaurant(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public Restaurant(UUID id, String name, CuisineType cuisineType, LocalTime openTime, LocalTime closeTime, User owner, Address address, List<Menu> menus) {
        setId(id);
        setName(name);
        setCuisineType(cuisineType);
        setOpenTime(openTime);
        setCloseTime(closeTime);
        setOwner(owner);
        setAddress(address);
        setMenus(menus);
    }

    public Restaurant(String name, CuisineType cuisineType, LocalTime openTime, LocalTime closeTime,  User owner, Address address, List<Menu> menus) {
        this.id = UUID.randomUUID();
        setName(name);
        setCuisineType(cuisineType);
        setOpenTime(openTime);
        setCloseTime(closeTime);
        setOwner(owner);
        setAddress(address);
        setMenus(menus);
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

    public User getOwner() {
        return owner;
    }

    public Address getAddress() {
        return address;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Restaurant id must be not null");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Restaurant name must be not null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Restaurant name must be not blank or empty");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Restaurant name must be not blank or empty");
        }
        this.name = name;
    }

    private void setCuisineType(CuisineType cuisineType) {
        if (cuisineType == null) {
            throw new IllegalArgumentException("Restaurant cuisine type must be not null");
        }
        this.cuisineType = cuisineType;
    }

    private void setOwner(User owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Restaurant owner type must be not null");
        }
        this.owner = owner;
    }

    private void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Restaurant address type must be not null");
        }
        this.address = address;
    }

    private void setMenus(List<Menu> menus) {
        if (menus == null) {
            throw new IllegalArgumentException("Restaurant menus type must be not null");
        }
        this.menus.clear();
        this.menus.addAll(menus);
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

}

package br.eng.eaa.infra.db.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Hidden
@Table(name = "tb_restaurant")
public class RestaurantEntity {

    @Id
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CuisineTypeEnum cuisineType;

    private LocalTime openTime;
    private LocalTime closeTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MenuEntity> menus;

    public RestaurantEntity() {
    }

    public RestaurantEntity(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public RestaurantEntity(UUID id, String name, CuisineTypeEnum cuisineType,LocalTime openTime, LocalTime closeTime, UserEntity owner, AddressEntity address, List<MenuEntity> menus) {
        setId(id);
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

    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateString(name, "Restaurant");
        this.name = name;
    }

    public CuisineTypeEnum getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineTypeEnum cuisineType) {
        this.cuisineType = cuisineType;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public RestaurantEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
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

    private void validateString(String value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " name cannot be null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " name cannot be empty");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " name cannot be blank");
        }
    }

}

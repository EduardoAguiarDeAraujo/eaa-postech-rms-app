package br.eng.eaa.application.model.response;

import java.math.BigDecimal;
import java.util.UUID;

public class MenuResponse {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    public MenuResponse(UUID id, String name, String description, BigDecimal price, Boolean available, String imageUrl, UUID restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UUID getRestaurant() {
        return restaurantId;
    }
}

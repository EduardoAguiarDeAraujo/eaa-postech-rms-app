package br.eng.eaa.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Menu  {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private UUID restaurantId;

    public Menu(String name, String description, BigDecimal price, Boolean available, String imageUrl, UUID restaurantId) {
        this(UUID.randomUUID(), name, description, price, available, imageUrl, restaurantId);
    }

    public Menu(UUID id, String name, String description, BigDecimal price, Boolean available, String imageUrl, UUID restaurantId) {
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setAvailable(available);
        setImageUrl(imageUrl);
        setRestaurantId(restaurantId);
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

    private void setId(UUID id) {
        validateNotNull(id, "ID");
        this.id = id;
    }

    private void setName(String name) {
        validateString(name, "name");
        this.name = name;
    }

    private void setDescription(String description) {
        validateString(description, "description");
        this.description = description;
    }

    private void setPrice(BigDecimal price) {
        validateNotNull(price, "price");
        this.price = price;
    }

    private void setAvailable(Boolean available) {
        validateNotNull(available, "available");
        this.available = available;
    }

    private void setImageUrl(String imageUrl) {
        validateString(imageUrl, "imageUrl");
        this.imageUrl = imageUrl;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        validateNotNull(restaurantId, "restaurantId");
        this.restaurantId = restaurantId;
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " name cannot be null, empty or blank");
        }
    }

    private void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null.");
        }
    }

}

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
        this.id = UUID.randomUUID();
        setName(name);
        setDescription(description);
        setPrice(price);
        setAvailable(available);
        setImageUrl(imageUrl);
        setRestaurantId(restaurantId);
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
        if (id == null) {
            throw new IllegalArgumentException("ID must be not null");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name must be not null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name must be not blank or empty");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("name must be not blank or empty");
        }
        this.name = name;
    }

    private void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("description must be not null");
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException("description must be not blank or empty");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("description must be not blank or empty");
        }
        this.description = description;
    }

    private void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("price must be not null");
        }
        this.price = price;
    }

    private void setAvailable(Boolean available) {
        if (available == null) {
            throw new IllegalArgumentException("available must be not null");
        }
        this.available = available;
    }

    private void setImageUrl(String imageUrl) {
        if (imageUrl == null) {
            throw new IllegalArgumentException("imageUrl must be not null");
        }
        if (imageUrl.isEmpty()) {
            throw new IllegalArgumentException("imageUrl must be not blank or empty");
        }
        if (imageUrl.isBlank()) {
            throw new IllegalArgumentException("imageUrl must be not blank or empty");
        }
        this.imageUrl = imageUrl;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        if (restaurantId == null) {
            throw new IllegalArgumentException("restaurantId must be not null");
        }
        this.restaurantId = restaurantId;
    }
}

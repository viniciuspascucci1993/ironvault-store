package com.ironvault.store.product.domain.model;


import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

    private UUID id;
    private UUID merchantId;
    private String name;
    private String description;
    private String imageUrl;
    private boolean active;
    private LocalDateTime createdAt;

    public Product() { }

    public static Product create(UUID merchantId, String name, String description, String imageUrl) {
        return new Product(
                UUID.randomUUID(),
                merchantId,
                name,
                description,
                imageUrl,
                true,
                LocalDateTime.now()
        );
    }

    public Product(UUID id, UUID merchantId, String name,
                   String description, String imageUrl,
                   boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.active = active;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(UUID merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

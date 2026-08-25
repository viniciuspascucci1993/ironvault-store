package com.ironvault.store.product.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductVariant {

    private UUID id;
    private UUID productId;
    private Integer size;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime createdAt;

    public ProductVariant() { }

    public static ProductVariant create(UUID productId, Integer size, BigDecimal price, Integer stock) {
        return new ProductVariant(
                UUID.randomUUID(),
                productId,
                size,
                price,
                stock,
                LocalDateTime.now()
        );
    }

    public ProductVariant(UUID id, UUID productId,
                          Integer size, BigDecimal price,
                          Integer stock, LocalDateTime createdAt) {
        this.id = id;
        this.productId = productId;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

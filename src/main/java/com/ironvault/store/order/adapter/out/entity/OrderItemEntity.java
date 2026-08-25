package com.ironvault.store.order.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "product_variant_id", nullable = false)
    private UUID productVariantId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer size;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    public OrderItemEntity() { }

    public OrderItemEntity(UUID id, UUID orderId, UUID productVariantId, String productName,
                           Integer size, BigDecimal unitPrice, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productVariantId = productVariantId;
        this.productName = productName;
        this.size = size;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(UUID productVariantId) {
        this.productVariantId = productVariantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

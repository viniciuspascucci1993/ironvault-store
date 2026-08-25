package com.ironvault.store.order.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {

    private UUID id;
    private UUID orderId;
    private UUID productVariantId;
    private String productName;
    private Integer size;
    private BigDecimal unitPrice;
    private Integer quantity;

    public OrderItem() { }

    public static OrderItem create(UUID orderId, UUID productVariantId, String productName,
                                   Integer size, BigDecimal unitPrice, Integer quantity) {
        return new OrderItem(
                UUID.randomUUID(),
                orderId,
                productVariantId,
                productName,
                size,
                unitPrice,
                quantity
        );
    }

    public OrderItem(UUID id, UUID orderId, UUID productVariantId,
                     String productName, Integer size, BigDecimal
                             unitPrice, Integer quantity) {
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

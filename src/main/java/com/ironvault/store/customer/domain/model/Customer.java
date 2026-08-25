package com.ironvault.store.customer.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Customer {

    private UUID id;
    private UUID merchantId;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    public Customer() { }

    public static Customer create(UUID merchantId, String name, String email, String phone) {
        return new Customer(
                UUID.randomUUID(),
                merchantId,
                name,
                email,
                phone,
                LocalDateTime.now()
        );
    }

    public Customer(UUID id, UUID merchantId, String name,
                    String email, String phone,
                    LocalDateTime createdAt) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

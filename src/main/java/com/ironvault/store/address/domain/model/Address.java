package com.ironvault.store.address.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Address {

    private UUID id;
    private UUID customerId;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private LocalDateTime createdAt;

    public Address() { }

    public static Address create(UUID customerId, String street, String number, String complement,
                                 String neighborhood, String city, String state, String zipCode) {
        return new Address(
                UUID.randomUUID(),
                customerId,
                street,
                number,
                complement,
                neighborhood,
                city,
                state,
                zipCode,
                LocalDateTime.now()
        );
    }

    public Address(UUID id, UUID customerId,
                   String street,
                   String number,
                   String complement,
                   String neighborhood,
                   String city,
                   String state,
                   String zipCode,
                   LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

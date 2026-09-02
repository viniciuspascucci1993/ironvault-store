package com.ironvault.store.checkout.adapter.in.dto;

import java.util.List;
import java.util.UUID;

public record CheckoutRequest(
    UUID merchantId,
    String customerName,
    String customerEmail,
    String customerPhone,
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String zipCode,
    List<ItemRequest> items
) {
    public record ItemRequest(UUID productVariantId, Integer quantity) { }
}

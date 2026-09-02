package com.ironvault.store.checkout.adapter.in.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CheckoutResponse(
    UUID orderId,
    BigDecimal totalAmount,
    String paymentId,
    String paymentStatus,
    String pixQrCode,
    String pixCopyPaste
) { }

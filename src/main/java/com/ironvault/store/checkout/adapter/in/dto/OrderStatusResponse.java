package com.ironvault.store.checkout.adapter.in.dto;

import com.ironvault.store.order.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderStatusResponse(
        UUID orderId,
        OrderStatus orderStatus,
        BigDecimal totalAmount,
        String paymentId,
        String paymentStatus,
        String pixQrCode,
        String pixCopyPaste
) { }

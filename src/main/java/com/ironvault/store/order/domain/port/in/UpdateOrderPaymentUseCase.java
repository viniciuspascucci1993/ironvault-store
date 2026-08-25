package com.ironvault.store.order.domain.port.in;

import com.ironvault.store.order.domain.model.Order;

import java.util.UUID;

public interface UpdateOrderPaymentUseCase {

    Order execute(UUID orderId, String paymentId);
}

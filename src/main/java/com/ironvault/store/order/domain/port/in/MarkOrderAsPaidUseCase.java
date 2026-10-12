package com.ironvault.store.order.domain.port.in;

import com.ironvault.store.order.domain.model.Order;

import java.util.Optional;

public interface MarkOrderAsPaidUseCase {

    Optional<Order> execute(String paymentId);
}

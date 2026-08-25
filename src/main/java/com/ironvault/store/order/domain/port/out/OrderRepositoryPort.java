package com.ironvault.store.order.domain.port.out;

import com.ironvault.store.order.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort {

    Order save(Order order);

    Optional<Order> findById(UUID id);
}

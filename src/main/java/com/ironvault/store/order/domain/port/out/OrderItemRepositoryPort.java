package com.ironvault.store.order.domain.port.out;

import com.ironvault.store.order.domain.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepositoryPort {

    List<OrderItem> saveAll(List<OrderItem> items);
    List<OrderItem> findByOrderId(UUID orderId);
}

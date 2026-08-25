package com.ironvault.store.order.adapter.out.persistence;

import com.ironvault.store.order.adapter.out.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, UUID> {

    List<OrderItemEntity> findByOrderId(UUID orderId);
}

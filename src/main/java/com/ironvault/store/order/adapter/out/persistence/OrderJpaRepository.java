package com.ironvault.store.order.adapter.out.persistence;

import com.ironvault.store.order.adapter.out.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}

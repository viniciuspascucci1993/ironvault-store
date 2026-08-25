package com.ironvault.store.order.adapter.out.persistence;

import com.ironvault.store.order.adapter.out.entity.OrderItemEntity;
import com.ironvault.store.order.domain.model.OrderItem;
import com.ironvault.store.order.domain.port.out.OrderItemRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderItemRepositoryAdapter implements OrderItemRepositoryPort {

    private final OrderItemJpaRepository jpaRepository;

    public OrderItemRepositoryAdapter(OrderItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> items) {
        List<OrderItemEntity> entities = items.stream()
                .map(this::toEntity)
                .toList();


        return jpaRepository.saveAll(entities)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<OrderItem> findByOrderId(UUID orderId) {
        return jpaRepository.findByOrderId(orderId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private OrderItemEntity toEntity(OrderItem orderItem) {
        return new OrderItemEntity(
                orderItem.getId(),
                orderItem.getOrderId(),
                orderItem.getProductVariantId(),
                orderItem.getProductName(),
                orderItem.getSize(),
                orderItem.getUnitPrice(),
                orderItem.getQuantity()
        );
    }

    private OrderItem toDomain(OrderItemEntity entity) {
        return new OrderItem(
                entity.getId(),
                entity.getOrderId(),
                entity.getProductVariantId(),
                entity.getProductName(),
                entity.getSize(),
                entity.getUnitPrice(),
                entity.getQuantity()
        );
    }
}

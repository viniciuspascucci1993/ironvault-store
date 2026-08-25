package com.ironvault.store.order.adapter.out.persistence;

import com.ironvault.store.order.adapter.out.entity.OrderEntity;
import com.ironvault.store.order.domain.model.Order;
import com.ironvault.store.order.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository jpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = toEntity(order);
        OrderEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getMerchantId(),
                order.getCustomerId(),
                order.getAddressId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getPaymentId(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }

    private Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getMerchantId(),
                entity.getCustomerId(),
                entity.getAddressId(),
                entity.getStatus(),
                entity.getTotalAmount(),
                entity.getPaymentId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}

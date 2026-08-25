package com.ironvault.store.order.application;

import com.ironvault.store.order.domain.model.Order;
import com.ironvault.store.order.domain.port.in.GetOrderByIdUseCase;
import com.ironvault.store.order.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetOrderByIdService implements GetOrderByIdUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public GetOrderByIdService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order getById(UUID id) {
        return orderRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}

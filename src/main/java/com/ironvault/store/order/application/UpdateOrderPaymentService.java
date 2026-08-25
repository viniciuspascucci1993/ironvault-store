package com.ironvault.store.order.application;

import com.ironvault.store.order.domain.model.Order;
import com.ironvault.store.order.domain.port.in.GetOrderByIdUseCase;
import com.ironvault.store.order.domain.port.in.UpdateOrderPaymentUseCase;
import com.ironvault.store.order.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UpdateOrderPaymentService implements UpdateOrderPaymentUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final GetOrderByIdUseCase getOrderByIdUseCase;

    public UpdateOrderPaymentService(OrderRepositoryPort orderRepositoryPort,
                                     GetOrderByIdUseCase getOrderByIdUseCase) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
    }

    @Override
    public Order execute(UUID orderId, String paymentId) {
        Order order = getOrderByIdUseCase.getById(orderId);
        order.setPaymentId(paymentId);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepositoryPort.save(order);
    }
}

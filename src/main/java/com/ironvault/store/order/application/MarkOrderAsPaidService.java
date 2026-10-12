package com.ironvault.store.order.application;

import com.ironvault.store.order.domain.enums.OrderStatus;
import com.ironvault.store.order.domain.model.Order;
import com.ironvault.store.order.domain.model.OrderItem;
import com.ironvault.store.order.domain.port.in.MarkOrderAsPaidUseCase;
import com.ironvault.store.order.domain.port.out.OrderItemRepositoryPort;
import com.ironvault.store.order.domain.port.out.OrderRepositoryPort;
import com.ironvault.store.product.domain.port.out.ProductVariantRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class MarkOrderAsPaidService implements MarkOrderAsPaidUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderItemRepositoryPort orderItemRepositoryPort;
    private final ProductVariantRepositoryPort productVariantRepositoryPort;

    public MarkOrderAsPaidService(OrderRepositoryPort orderRepositoryPort,
                                  OrderItemRepositoryPort orderItemRepositoryPort,
                                  ProductVariantRepositoryPort productVariantRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderItemRepositoryPort = orderItemRepositoryPort;
        this.productVariantRepositoryPort = productVariantRepositoryPort;
    }

    @Override
    @Transactional
    public Optional<Order> execute(String paymentId) {
        Optional<Order> found = orderRepositoryPort.findByPaymentId(paymentId);

        if (found.isEmpty()) {
            return Optional.empty();
        }

        Order order = found.get();

        if (order.getStatus() != OrderStatus.PENDING) {
            return Optional.of(order);
        }

        for (OrderItem item : orderItemRepositoryPort.findByOrderId(order.getId())) {
            boolean decremented = productVariantRepositoryPort
                    .decrementStock(item.getProductVariantId(), item.getQuantity());

            if (!decremented) {
                // O cliente ja pagou, então permanece como PAID mesmo assim
                log.error("OVERSOLD: sem estoque para variant {} no pedido {} (pago)",
                        item.getProductVariantId(), order.getId());
            }
        }

        order.setStatus(OrderStatus.PAID);
        order.setUpdatedAt(LocalDateTime.now());

        return Optional.of(orderRepositoryPort.save(order));
    }
}

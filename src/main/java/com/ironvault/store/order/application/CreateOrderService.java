package com.ironvault.store.order.application;

import com.ironvault.store.order.domain.model.Order;
import com.ironvault.store.order.domain.model.OrderItem;
import com.ironvault.store.order.domain.port.in.CreateOrderUseCase;
import com.ironvault.store.order.domain.port.out.OrderItemRepositoryPort;
import com.ironvault.store.order.domain.port.out.OrderRepositoryPort;
import com.ironvault.store.product.domain.model.ProductVariant;
import com.ironvault.store.product.domain.port.in.GetProductByIdUseCase;
import com.ironvault.store.product.domain.port.out.ProductVariantRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderItemRepositoryPort orderItemRepositoryPort;
    private final ProductVariantRepositoryPort productVariantRepositoryPort;
    private final GetProductByIdUseCase getProductByIdUseCase;

    public CreateOrderService(OrderRepositoryPort orderRepositoryPort,
                              OrderItemRepositoryPort orderItemRepositoryPort,
                              ProductVariantRepositoryPort productVariantRepositoryPort,
                              GetProductByIdUseCase getProductByIdUseCase) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderItemRepositoryPort = orderItemRepositoryPort;
        this.productVariantRepositoryPort = productVariantRepositoryPort;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    @Override
    @Transactional
    public Order execute(UUID merchantId, UUID customerId, UUID addressId, List<ItemInput> items) {


        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        // Primeiro passamos por todos os items: validamos estoque e montamos a lista
        List<ProductVariant> variants = new ArrayList<>();

        for (ItemInput item : items) {
            ProductVariant variant =  productVariantRepositoryPort.findById(item.productVariantId())
                    .orElseThrow(() -> new IllegalArgumentException("Product Variant not found: " + item.productVariantId()));


            if (variant.getStock() < item.quantity()) {
                throw new IllegalArgumentException("Insufficient stock for variant: " + item.productVariantId());
            }

            variants.add(variant);
        }

        // Depois criamos o pedido ( ainda sme id definitivo dos items )
        Order order = Order.create(merchantId, customerId, addressId, BigDecimal.ZERO);
        Order savedOrder = orderRepositoryPort.save(order);

        // Montando os items com preço/nome reais, buscando do product
        for (int i = 0; i < items.size(); i++) {
            ItemInput item = items.get(i);
            ProductVariant variant = variants.get(i);

            var product = getProductByIdUseCase.getById(variant.getProductId());

            BigDecimal itemTotal = variant.getPrice().multiply(BigDecimal.valueOf(item.quantity()));
            totalAmount = totalAmount.add(itemTotal);

            OrderItem orderItem = OrderItem.create(
                    savedOrder.getId(),
                    variant.getId(),
                    product.getName(),
                    variant.getSize(),
                    variant.getPrice(),
                    item.quantity()
            );

            orderItems.add(orderItem);
        }

        orderItemRepositoryPort.saveAll(orderItems);

        //Por fim atualizamos o pedido com o total calculado
        savedOrder.setTotalAmount(totalAmount);
        return orderRepositoryPort.save(savedOrder);
    }
}

package com.ironvault.store.order.domain.port.in;

import com.ironvault.store.order.domain.model.Order;

import java.util.List;
import java.util.UUID;

public interface CreateOrderUseCase {

    Order execute(UUID merchantId, UUID customerId, UUID addressId, List<ItemInput> items);

    record ItemInput(UUID productVariantId, Integer quantity) { }
}

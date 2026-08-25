package com.ironvault.store.product.domain.port.in;

import com.ironvault.store.product.domain.model.Product;

import java.util.UUID;

public interface GetProductByIdUseCase {

    Product getById(UUID id);
}

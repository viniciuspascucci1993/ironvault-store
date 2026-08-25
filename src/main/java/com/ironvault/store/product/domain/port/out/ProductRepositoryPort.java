package com.ironvault.store.product.domain.port.out;

import com.ironvault.store.product.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {

    Product save(Product product);
    Optional<Product> findById(UUID id);
    List<Product> findByMerchantId(UUID merchantId);
}

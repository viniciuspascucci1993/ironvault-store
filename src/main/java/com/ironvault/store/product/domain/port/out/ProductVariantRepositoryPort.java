package com.ironvault.store.product.domain.port.out;

import com.ironvault.store.product.domain.model.ProductVariant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductVariantRepositoryPort {

    List<ProductVariant> saveAll(List<ProductVariant> variants);
    List<ProductVariant> findByProductId(UUID productId);
    Optional<ProductVariant> findById(UUID id);
}

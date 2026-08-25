package com.ironvault.store.product.domain.port.in;

import com.ironvault.store.product.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CreateProductUseCase {

    Product execute(UUID merchantId, String name, String description, String imageUrl, List<VariantInput> variants);
    record VariantInput(Integer size, BigDecimal price, Integer stock) { }
}

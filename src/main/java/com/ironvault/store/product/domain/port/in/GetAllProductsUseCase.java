package com.ironvault.store.product.domain.port.in;

import com.ironvault.store.product.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface GetAllProductsUseCase {

    List<Product> getByMerchantId(UUID merchantId);
}

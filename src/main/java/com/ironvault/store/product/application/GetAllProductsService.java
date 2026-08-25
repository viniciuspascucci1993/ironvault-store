package com.ironvault.store.product.application;

import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.in.GetAllProductsUseCase;
import com.ironvault.store.product.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAllProductsService implements GetAllProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetAllProductsService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<Product> getByMerchantId(UUID merchantId) {
        return productRepositoryPort.findByMerchantId(merchantId);
    }
}

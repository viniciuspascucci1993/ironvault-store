package com.ironvault.store.product.application;

import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.in.GetProductByIdUseCase;
import com.ironvault.store.product.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProductByIdService implements GetProductByIdUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductByIdService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product getById(UUID id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product Not Found " + id));
    }
}

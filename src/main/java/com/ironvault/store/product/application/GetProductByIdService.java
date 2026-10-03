package com.ironvault.store.product.application;

import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.in.GetProductByIdUseCase;
import com.ironvault.store.product.domain.port.out.ProductRepositoryPort;
import com.ironvault.store.product.domain.port.out.ProductVariantRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetProductByIdService implements GetProductByIdUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductVariantRepositoryPort productVariantRepositoryPort;

    public GetProductByIdService(ProductRepositoryPort productRepositoryPort,
                            ProductVariantRepositoryPort productVariantRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productVariantRepositoryPort = productVariantRepositoryPort;
    }

    @Override
    public Product getById(UUID id) {
        Product product = productRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product Not Found " + id));
        product.setVariants(productVariantRepositoryPort.findByProductId(product.getId()));

        return product;

    }
}

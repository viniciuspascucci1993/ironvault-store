package com.ironvault.store.product.application;

import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.in.GetAllProductsUseCase;
import com.ironvault.store.product.domain.port.out.ProductRepositoryPort;
import com.ironvault.store.product.domain.port.out.ProductVariantRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAllProductsService implements GetAllProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductVariantRepositoryPort productVariantRepositoryPort;

    public GetAllProductsService(ProductRepositoryPort productRepositoryPort,
                                 ProductVariantRepositoryPort productVariantRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productVariantRepositoryPort = productVariantRepositoryPort;
    }

    @Override
    public List<Product> getByMerchantId(UUID merchantId) {

        List<Product> products = productRepositoryPort.findByMerchantId(merchantId);
        products.forEach(p ->
                p.setVariants(productVariantRepositoryPort.findByProductId(p.getId())));

        return products;
    }
}

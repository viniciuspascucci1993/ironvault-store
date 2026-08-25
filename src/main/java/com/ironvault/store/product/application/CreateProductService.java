package com.ironvault.store.product.application;

import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.model.ProductVariant;
import com.ironvault.store.product.domain.port.in.CreateProductUseCase;
import com.ironvault.store.product.domain.port.out.ProductRepositoryPort;
import com.ironvault.store.product.domain.port.out.ProductVariantRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductVariantRepositoryPort productVariantRepositoryPort;

    public CreateProductService(ProductRepositoryPort productRepositoryPort,
                                ProductVariantRepositoryPort productVariantRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productVariantRepositoryPort = productVariantRepositoryPort;
    }

    @Override
    @Transactional
    public Product execute(UUID merchantId, String name,
           String description, String imageUrl, List<VariantInput> variants) {

        Product product = Product.create(merchantId, name, description, imageUrl);
        Product saveProduct = productRepositoryPort.save(product);

        List<ProductVariant> productVariants = variants.stream()
                .map(v -> ProductVariant.create(saveProduct.getId(), v.size(), v.price(), v.stock()))
                .toList();

        productVariantRepositoryPort.saveAll(productVariants);
        return saveProduct;
    }
}

package com.ironvault.store.product.adapter.out.persistence;

import com.ironvault.store.product.adapter.out.entity.ProductEntity;
import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Product> findByMerchantId(UUID merchantId) {
        return jpaRepository.findByMerchantId(merchantId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getMerchantId(),
                product.getName(),
                product.getDescription(),
                product.getImageUrl(),
                product.isActive(),
                product.getCreatedAt()
        );
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getMerchantId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.isActive(),
                entity.getCreatedAt()
        );
    }
}

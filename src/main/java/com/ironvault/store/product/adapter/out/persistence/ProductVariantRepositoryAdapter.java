package com.ironvault.store.product.adapter.out.persistence;

import com.ironvault.store.product.adapter.out.entity.ProductVariantEntity;
import com.ironvault.store.product.domain.model.ProductVariant;
import com.ironvault.store.product.domain.port.out.ProductVariantRepositoryPort;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductVariantRepositoryAdapter implements ProductVariantRepositoryPort {

    private final ProductVariantJpaRepository jpaRepository;

    public ProductVariantRepositoryAdapter(ProductVariantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ProductVariant> saveAll(List<ProductVariant> variants) {

        List<ProductVariantEntity> entities = variants.stream()
                .map(this::toEntity)
                .toList();

        return jpaRepository.saveAll(entities)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<ProductVariant> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<ProductVariant> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private ProductVariantEntity toEntity(ProductVariant variant) {
        return new ProductVariantEntity(
                variant.getId(),
                variant.getProductId(),
                variant.getSize(),
                variant.getPrice(),
                variant.getStock(),
                variant.getCreatedAt()
        );
    }

    private ProductVariant toDomain(ProductVariantEntity entity) {
        return new ProductVariant(
                entity.getId(),
                entity.getProductId(),
                entity.getSize(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCreatedAt()
        );
    }
}

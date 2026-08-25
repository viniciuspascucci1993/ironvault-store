package com.ironvault.store.product.adapter.out.persistence;

import com.ironvault.store.product.adapter.out.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductVariantJpaRepository extends JpaRepository<ProductVariantEntity, UUID> {

    List<ProductVariantEntity> findByProductId(UUID productId);
}

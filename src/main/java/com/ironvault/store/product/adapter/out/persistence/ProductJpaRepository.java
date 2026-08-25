package com.ironvault.store.product.adapter.out.persistence;

import com.ironvault.store.product.adapter.out.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findByMerchantId(UUID merchantId);
}

package com.ironvault.store.product.adapter.out.persistence;

import com.ironvault.store.product.adapter.out.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductVariantJpaRepository extends JpaRepository<ProductVariantEntity, UUID> {

    List<ProductVariantEntity> findByProductId(UUID productId);

    @Modifying
    @Query("update ProductVariantEntity v set v.stock = v.stock - :qty " +
            "where v.id = :id and v.stock >= :qty")
    int decrementStock(@Param("id") UUID id, @Param("qty") int qty);
}

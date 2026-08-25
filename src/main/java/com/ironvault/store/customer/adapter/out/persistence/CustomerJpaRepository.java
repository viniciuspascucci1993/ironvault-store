package com.ironvault.store.customer.adapter.out.persistence;

import com.ironvault.store.customer.adapter.out.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByMerchantIdAndEmail(UUID merchantId, String email);
}

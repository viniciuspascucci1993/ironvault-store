package com.ironvault.store.address.adapter.out.persistence;

import com.ironvault.store.address.adapter.out.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, UUID> {

    List<AddressEntity> findByCustomerId(UUID customerId);
}

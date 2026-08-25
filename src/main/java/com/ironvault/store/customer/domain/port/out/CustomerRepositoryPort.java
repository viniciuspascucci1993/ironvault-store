package com.ironvault.store.customer.domain.port.out;

import com.ironvault.store.customer.domain.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepositoryPort {

    Customer save(Customer customer);
    Optional<Customer> findById(UUID id);
    Optional<Customer> findByMerchantIdAndEmail(UUID merchantId, String email);
}

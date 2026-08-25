package com.ironvault.store.customer.adapter.out.persistence;

import com.ironvault.store.customer.adapter.out.entity.CustomerEntity;
import com.ironvault.store.customer.domain.model.Customer;
import com.ironvault.store.customer.domain.port.out.CustomerRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final CustomerJpaRepository jpaRepository;

    public CustomerRepositoryAdapter(CustomerJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = toEntity(customer);
        CustomerEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Customer> findByMerchantIdAndEmail(UUID merchantId, String email) {
        return jpaRepository.findByMerchantIdAndEmail(merchantId, email).map(this::toDomain);
    }

    private CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(
                customer.getId(),
                customer.getMerchantId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt()
        );
    }

    private Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getMerchantId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getCreatedAt()
        );
    }
}

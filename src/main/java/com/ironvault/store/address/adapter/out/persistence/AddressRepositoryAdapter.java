package com.ironvault.store.address.adapter.out.persistence;

import com.ironvault.store.address.adapter.out.entity.AddressEntity;
import com.ironvault.store.address.domain.model.Address;
import com.ironvault.store.address.domain.port.out.AddressRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AddressRepositoryAdapter implements AddressRepositoryPort {

    private final AddressJpaRepository jpaRepository;

    public AddressRepositoryAdapter(AddressJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Address save(Address address) {
        AddressEntity entity = toEntity(address);
        AddressEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Address> findByCustomerId(UUID customerId) {
        return jpaRepository.findByCustomerId(customerId).stream()
                .map(this::toDomain)
                .toList();
    }

    private AddressEntity toEntity(Address address) {
        return new AddressEntity(
                address.getId(),
                address.getCustomerId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCreatedAt()
        );
    }

    private Address toDomain(AddressEntity entity) {
        return new Address(
                entity.getId(),
                entity.getCustomerId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode(),
                entity.getCreatedAt()
        );
    }
}

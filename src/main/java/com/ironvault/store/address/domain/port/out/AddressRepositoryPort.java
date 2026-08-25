package com.ironvault.store.address.domain.port.out;

import com.ironvault.store.address.domain.model.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepositoryPort {

    Address save(Address address);

    List<Address> findByCustomerId(UUID customerId);
}

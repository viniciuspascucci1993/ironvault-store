package com.ironvault.store.address.domain.port.in;

import com.ironvault.store.address.domain.model.Address;

import java.util.List;
import java.util.UUID;

public interface GetAddressesByCustomerUseCase {

    List<Address> getByCustomerId(UUID customerId);
}

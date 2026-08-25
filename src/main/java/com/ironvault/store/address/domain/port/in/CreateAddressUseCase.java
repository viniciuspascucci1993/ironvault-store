package com.ironvault.store.address.domain.port.in;

import com.ironvault.store.address.domain.model.Address;

import java.util.UUID;

public interface CreateAddressUseCase {

    Address execute(UUID customerId, String street, String number, String complement,
                    String neighborhood, String city, String state, String zipCode);
}

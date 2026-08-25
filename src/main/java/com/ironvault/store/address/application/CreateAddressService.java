package com.ironvault.store.address.application;

import com.ironvault.store.address.domain.model.Address;
import com.ironvault.store.address.domain.port.in.CreateAddressUseCase;
import com.ironvault.store.address.domain.port.out.AddressRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateAddressService implements CreateAddressUseCase {

    private final AddressRepositoryPort addressRepositoryPort;

    public CreateAddressService(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }

    @Override
    public Address execute(UUID customerId, String street, String number,
                           String complement, String neighborhood,
                           String city, String state, String zipCode) {

        Address address = Address.create(customerId, street, number, complement,
                neighborhood, city, state, zipCode);
        return addressRepositoryPort.save(address);
    }
}

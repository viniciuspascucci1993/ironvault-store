package com.ironvault.store.address.application;

import com.ironvault.store.address.domain.model.Address;
import com.ironvault.store.address.domain.port.in.GetAddressesByCustomerUseCase;
import com.ironvault.store.address.domain.port.out.AddressRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAddressesByCustomerService implements GetAddressesByCustomerUseCase {

    private final AddressRepositoryPort addressRepositoryPort;

    public GetAddressesByCustomerService(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }

    @Override
    public List<Address> getByCustomerId(UUID customerId) {
        return addressRepositoryPort.findByCustomerId(customerId);
    }
}

package com.ironvault.store.customer.application;

import com.ironvault.store.customer.domain.model.Customer;
import com.ironvault.store.customer.domain.port.in.GetCustomerByIdUseCase;
import com.ironvault.store.customer.domain.port.out.CustomerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerByIdService implements GetCustomerByIdUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public GetCustomerByIdService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer getById(UUID id) {
        return customerRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }
}

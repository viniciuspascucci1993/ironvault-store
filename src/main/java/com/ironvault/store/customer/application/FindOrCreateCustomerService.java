package com.ironvault.store.customer.application;

import com.ironvault.store.customer.domain.model.Customer;
import com.ironvault.store.customer.domain.port.in.FindOrCreateCustomerUseCase;
import com.ironvault.store.customer.domain.port.out.CustomerRepositoryPort;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.UUID;

@Service
public class FindOrCreateCustomerService implements FindOrCreateCustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public FindOrCreateCustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer execute(UUID merchantId, String name, String email, String phone) {
        return customerRepositoryPort.findByMerchantIdAndEmail(merchantId, email)
                .orElseGet(() -> {
                    Customer newCustomer = Customer.create(merchantId, name, email, phone);
                    return customerRepositoryPort.save(newCustomer);
                });
    }
}

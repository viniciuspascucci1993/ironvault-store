package com.ironvault.store.customer.domain.port.in;

import com.ironvault.store.customer.domain.model.Customer;

import java.util.UUID;

public interface GetCustomerByIdUseCase {

    Customer getById(UUID id);
}

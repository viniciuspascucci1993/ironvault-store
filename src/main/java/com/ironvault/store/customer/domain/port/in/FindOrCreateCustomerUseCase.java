package com.ironvault.store.customer.domain.port.in;

import com.ironvault.store.customer.domain.model.Customer;

import java.rmi.server.UID;
import java.util.UUID;

public interface FindOrCreateCustomerUseCase {

    Customer execute(UUID merchantId, String name, String email, String phone);
}

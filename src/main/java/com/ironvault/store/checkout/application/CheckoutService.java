package com.ironvault.store.checkout.application;

import com.ironvault.store.address.domain.model.Address;
import com.ironvault.store.address.domain.port.in.CreateAddressUseCase;
import com.ironvault.store.checkout.adapter.in.dto.CheckoutRequest;
import com.ironvault.store.checkout.adapter.in.dto.CheckoutResponse;
import com.ironvault.store.checkout.adapter.out.PaymentClient;
import com.ironvault.store.customer.domain.model.Customer;
import com.ironvault.store.customer.domain.port.in.FindOrCreateCustomerUseCase;
import com.ironvault.store.order.domain.model.Order;
import com.ironvault.store.order.domain.port.in.CreateOrderUseCase;
import com.ironvault.store.order.domain.port.in.UpdateOrderPaymentUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    private final FindOrCreateCustomerUseCase findOrCreateCustomerUseCase;
    private final CreateAddressUseCase createAddressUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderPaymentUseCase updateOrderPaymentUseCase;
    private final PaymentClient paymentClient;

    public CheckoutService(FindOrCreateCustomerUseCase findOrCreateCustomerUseCase,
                           CreateAddressUseCase createAddressUseCase,
                           CreateOrderUseCase createOrderUseCase,
                           UpdateOrderPaymentUseCase updateOrderPaymentUseCase,
                           PaymentClient paymentClient) {
        this.findOrCreateCustomerUseCase = findOrCreateCustomerUseCase;
        this.createAddressUseCase = createAddressUseCase;
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderPaymentUseCase = updateOrderPaymentUseCase;
        this.paymentClient = paymentClient;
    }

    public CheckoutResponse execute(CheckoutRequest request) {

        // 1 - Busca ou cria o cliente
        Customer customer = findOrCreateCustomerUseCase.execute(
                request.merchantId(),
                request.customerName(),
                request.customerEmail(),
                request.customerPhone()
        );

        // 2 - Salva o endereço do cliente
        Address address = createAddressUseCase.execute(
                customer.getId(),
                request.street(),
                request.number(),
                request.complement(),
                request.neighborhood(),
                request.city(),
                request.state(),
                request.zipCode()
        );

        // 3 - Criamos o pedido do cliente (valida estoque, calcula total, busca preço real)
        List<CreateOrderUseCase.ItemInput> itemInputs = request.items().stream()
                .map(i -> new CreateOrderUseCase.ItemInput( i.productVariantId(), i.quantity() ))
                .toList();

        Order order = createOrderUseCase.execute(
                request.merchantId(),
                customer.getId(),
                address.getId(),
                itemInputs
        );

        // 4 - Cria o pagamento em ironvault-payments
        String idempotencyKey = "ironvault-checkout-" + order.getId();
        PaymentClient.PaymentResult paymentResult = paymentClient.createPayment(
                request.merchantId(),
                order.getTotalAmount(),
                "Pedido " + order.getId(),
                request.customerEmail(),
                idempotencyKey
        );

        // 5 - Vincula o paymentId ao pedido
        Order updateOrder = updateOrderPaymentUseCase.execute(order.getId(), paymentResult.id());

        return new CheckoutResponse(
                updateOrder.getId(),
                updateOrder.getTotalAmount(),
                paymentResult.id(),
                paymentResult.status(),
                paymentResult.pixQrCode(),
                paymentResult.pixCopyPaste()
        );
    }
}

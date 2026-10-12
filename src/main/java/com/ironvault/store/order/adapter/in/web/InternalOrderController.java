package com.ironvault.store.order.adapter.in.web;

import com.ironvault.store.order.domain.port.in.MarkOrderAsPaidUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/internal/orders")
public class InternalOrderController {

    @Value("${app.payments.internal-api-key}")
    private String internalApiKey;

    private final MarkOrderAsPaidUseCase markOrderAsPaidUseCase;

    public InternalOrderController(MarkOrderAsPaidUseCase markOrderAsPaidUseCase) {
        this.markOrderAsPaidUseCase = markOrderAsPaidUseCase;
    }

    @PostMapping("/paid")
    public ResponseEntity<?> markAsPaid(
            @RequestHeader("X-Internal-Key") String internalKey,
            @RequestBody PaymentApprovedRequest request) {

        if (!internalApiKey.equals(internalKey)) {
            return ResponseEntity.status(401).build();
        }

        return markOrderAsPaidUseCase.execute(request.paymentId())
                .<ResponseEntity<?>>map(order -> ResponseEntity.ok(
                        Map.of("orderId", order.getId(), "status", order.getStatus())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public record PaymentApprovedRequest(String paymentId) { }
}

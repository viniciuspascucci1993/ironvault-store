package com.ironvault.store.checkout.adapter.out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
@Slf4j
public class PaymentClient {

    private final RestTemplate restTemplate;

    @Value("${app.payments.url}")
    private String paymentUrl;

    @Value("${app.payments.internal-api-key}")
    private String internalApikey;

    public PaymentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentResult createPayment(UUID merchantId, BigDecimal amount, String description,
                                       String payerEmail, String idempotencyKey) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Key", internalApikey);
        headers.set("Idempotency-Key", idempotencyKey);

        Map<String, Object> body = Map.of(
                "merchantId", merchantId.toString(),
                "amount", amount,
                "currency", "BRL",
                "paymentMethod", "PIX",
                "description", description,
                "payerEmail", payerEmail
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        Map<?, ?> response = restTemplate.postForObject(
                paymentUrl + "/api/internal/payments",
                request,
                Map.class
        );

        return toPaymentResult(response);
    }

    public PaymentResult getPaymentStatus(String paymentId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Key", internalApikey);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        var response = restTemplate.exchange(
            paymentUrl + "/api/internal/payments" + paymentId,
                HttpMethod.GET,
                request,
                Map.class
        );

        return toPaymentResult(response.getBody());
    }

    private PaymentResult toPaymentResult(Map<?, ?> body) {
        if (body == null) {
            throw new IllegalArgumentException("Empty response for payments service");
        }

        return new PaymentResult(
                (String) body.get("id"),
                (String) body.get("status"),
                (String) body.get("pixQrCode"),
                (String) body.get("pixCopyPaste")
        );
    }

    public record PaymentResult(String id, String status, String pixQrCode, String pixCopyPaste) { }
}

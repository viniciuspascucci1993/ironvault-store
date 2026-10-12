package com.ironvault.store.checkout.adapter.in.web;

import com.ironvault.store.checkout.adapter.in.dto.CheckoutRequest;
import com.ironvault.store.checkout.adapter.in.dto.CheckoutResponse;
import com.ironvault.store.checkout.adapter.in.dto.OrderStatusResponse;
import com.ironvault.store.checkout.application.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest request) {
        CheckoutResponse response = checkoutService.execute(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderStatusResponse> getOrderStatus(@PathVariable("id")UUID id) {
        return ResponseEntity.ok(checkoutService.getOrderStatus(id));
    }
}

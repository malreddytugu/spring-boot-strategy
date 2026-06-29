package com.mrlabs.strategy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentFactory strategyFactory;

    // Dependency Injection via Constructor
    public PaymentController(PaymentFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {
        try {
            // 1. Fetch the correct strategy based on JSON input (e.g., "upi", "creditcard")
            PaymentStrategy strategy = strategyFactory.getStrategy(request.getMethod());
            
            // 2. Execute payment logic
            strategy.pay(request);
            
            return ResponseEntity.ok("Payment processed successfully using " + request.getMethod());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            		//internalServerError().body("Payment failed: " + e.getMessage());
        }
    }
}
package com.mrlabs.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PaymentStrategyFactory {

    // Spring automatically injects all concrete implementations of PaymentStrategy into this map
    private final Map<String, PaymentStrategy> strategies;

    public PaymentStrategyFactory(Map<String, PaymentStrategy> strategies) {
        this.strategies = strategies;
    }

    public PaymentStrategy getStrategy(String paymentMethod) {
        // Formulates the expected bean name, e.g., "UPI" becomes "upiPayment"
        String beanName = paymentMethod.toLowerCase() + "Payment";
        PaymentStrategy strategy = strategies.get(beanName);
        
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
        return strategy;
    }
}
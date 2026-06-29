package com.mrlabs.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {
	
	public Map<String,PaymentStrategy> strategies;

	public PaymentFactory(Map<String, PaymentStrategy> strategies) {
		super();
		this.strategies = strategies;
	}
	
	public PaymentStrategy getStrategy(String paymentMethod) {
		
		PaymentStrategy strategy = strategies.get(paymentMethod.toLowerCase()+"Payment");
		if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
		return strategy;
	}

}

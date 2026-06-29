package com.mrlabs.strategy;

import org.springframework.stereotype.Component;

@Component("upiPayment")
public class UpiPaymentStrategy implements PaymentStrategy {
	@Override
	public void pay(PaymentRequest request) {
		System.out.println("Processing UPI payment of $" + request.getAmount() + " for " + request.getUpiId());
	}
}

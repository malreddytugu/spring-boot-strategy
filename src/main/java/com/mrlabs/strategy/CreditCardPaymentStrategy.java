package com.mrlabs.strategy;

import org.springframework.stereotype.Component;

@Component("creditcardPayment") // Bean name target matching factory logic
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(PaymentRequest request) {
        System.out.println("Processing Credit Card payment of $" + request.getAmount());
        // Card specific logic here...
    }
}


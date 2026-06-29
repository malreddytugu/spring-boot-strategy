package com.mrlabs.strategy;

import org.springframework.stereotype.Component;

@Component("netbankingPayment")
public class NetBankingPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(PaymentRequest request) {
        System.out.println("Redirecting to bank " + request.getBankId() + " for amount " + request.getAmount());
    }
}
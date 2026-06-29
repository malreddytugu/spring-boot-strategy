package com.mrlabs.strategy;
public class PaymentRequest {
    private double amount;
    private String method; // CREDIT_CARD, UPI, NET_BANKING
    private String cardNumber;
    private String cvv;
    private String upiId;
    private String bankId;
    // Getters and Setters
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public String getCardNumber() { return cardNumber; }
    public String getCvv() { return cvv; }
    public String getUpiId() { return upiId; }
    public String getBankId() { return bankId; }
}
package com.app.designpatterns.creational.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

interface Payment {
    void processPayment(double amount);
    String getPaymentType();
}

enum PaymentType {
    CREDIT_CARD, UPI, PAYPAL, NET_BANKING
}

class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("[CreditCard] Processing " + amount);
    }
    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}

class UPIPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("[UPI] Processing " + amount);
    }
    @Override
    public String getPaymentType() {
        return "UPI";
    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("[PayPal] Processing " + amount);
    }
    @Override
    public String getPaymentType() {
        return "PayPal";
    }
}

class NetBankingPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("[NetBanking] Processing " + amount);
    }
    @Override
    public String getPaymentType() {
        return "Net Banking";
    }
}

class PaymentFactory {
    public static Payment getPaymentMethod(PaymentType type) throws IllegalAccessException {
        switch (type) {
            case CREDIT_CARD:
                return new CreditCardPayment();
            case UPI:
                return new UPIPayment();
            case PAYPAL:
                return new PayPalPayment();
            case NET_BANKING:
                return new NetBankingPayment();
            default:
                throw new IllegalAccessException("Invalid Payment Type");
        }
    }
}

class TransactionLogger {
    private static final List<String> logs = new ArrayList<>();

    public static void logTransaction(PaymentType type, double amount) {
        String logEntry = "Transaction: " + type + " | Amount: ₹" + amount + " | Time: " + new Date();
        logs.add(logEntry);
        System.out.println("[LOG] " + logEntry);
    }
    public static void showTransactionHistory() {
        System.out.println("\n📝 Transaction History:");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

class PaymentService {
    public static void processTransaction(PaymentType type, double amount) {
        try {
            Payment payment = PaymentFactory.getPaymentMethod(type);
            payment.processPayment(amount);
            TransactionLogger.logTransaction(type, amount);
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class EcommerceApp {

    public static void main(String[] args) {

        PaymentService.processTransaction(PaymentType.CREDIT_CARD, 1000);
        PaymentService.processTransaction(PaymentType.UPI, 500);
        PaymentService.processTransaction(PaymentType.PAYPAL, 2000);
        PaymentService.processTransaction(PaymentType.NET_BANKING, 1500);

        TransactionLogger.showTransactionHistory();
    }
}

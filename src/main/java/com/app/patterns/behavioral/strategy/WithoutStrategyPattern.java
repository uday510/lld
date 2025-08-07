//package com.app.patterns.behavioral.strategy;
//
//
//class PaymentService {
//
//    public void processPayment(String paymentMethod) {
//
//        if (paymentMethod.equalsIgnoreCase("credit card")) {
//            System.out.println("Processing payment using credit card");
//        } else if (paymentMethod.equalsIgnoreCase("upi")) {
//            System.out.println("Processing payment using upi");
//        } else if (paymentMethod.equalsIgnoreCase("debit card")) {
//            System.out.println("Processing payment using debit card");
//        } else {
//            System.out.println("Unsupported payment method");
//        }
//    }
//}
//
//
//public class WithoutStrategyPattern {
//
//    public static void main(String[] args) {
//
//        PaymentService paymentService = new PaymentService();
//
//        paymentService.processPayment("upi");
//        paymentService.processPayment("debit card");
//        paymentService.processPayment("credit card");
//
//    }
//
//}

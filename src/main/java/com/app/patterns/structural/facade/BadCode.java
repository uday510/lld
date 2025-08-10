package com.app.patterns.structural.facade;

public class BadCode {


    public static void main(String[] args) {

        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();


        // Task
        System.out.println(userService.getUserDetails("10"));
        System.out.println(orderService.getOrderDetails("10"));
        System.out.println(paymentService.processPayment("10"));

    }
}

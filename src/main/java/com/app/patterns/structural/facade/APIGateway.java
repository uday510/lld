package com.app.patterns.structural.facade;

public class APIGateway {

    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public APIGateway() {
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
    }

    public String getFullOrderDetails(String userId, String orderId, String paymentId) {
        return userService.getUserDetails(userId) + "\n" + orderService.getOrderDetails(orderId) + "\n" + paymentService.processPayment(paymentId);
    }
}

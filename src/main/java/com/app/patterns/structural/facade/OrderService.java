package com.app.patterns.structural.facade;

public class OrderService {

    public String getOrderDetails(String orderId) {
        return "Order Details [ orderId: " + orderId + " ]";
    }
}

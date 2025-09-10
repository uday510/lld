package com.app.solid.shopping_app;

public class RegularCustomer implements Customer {

    @Override
    public void placeOrder(Order order) {
        System.out.println("Customer placed an order");
        order.checkout();
    }
    
}
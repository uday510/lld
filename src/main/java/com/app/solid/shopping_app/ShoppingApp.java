package com.app.solid.shopping_app;



/**

S → Product, Cart, Payment, and Order each have one responsibility.

O → We can add new payments (e.g., UPIPayment) without touching old code.

L → All payment types (CreditCardPayment, PayPalPayment) work interchangeably.

I → Customers don’t need inventory methods; Admin doesn’t need order methods.

D → OrderService depends on PaymentMethod interface, not concrete classes.

 */
public class ShoppingApp {

    public static void main(String[] args) {

            Product laptop = new Product("Laptop", 1000);
            Product mouse = new Product("Mouse", 50);

            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(laptop);
            cart.addProduct(mouse);

            PaymentMethod payment = new CreditCardPayment();
            Order order = new Order(cart, payment);


            RegularCustomer customer = new RegularCustomer();
            customer.placeOrder();


            storeAdmin admin = new storeAdmin();
            admin.managetInventory();
    }

}
// High-level OrderService depends on Payment abstraction

import com.app.oops.paymentservice.PaymentMethod;

class OrderService {
    private PaymentMethod payment;

    public OrderService(PaymentMethod payment) {
        this.payment = payment;
    }

    public void processOrder(ShoppingCart cart) {
        double total = cart.calculateTotal();
        payment.pay(total);
    }
}
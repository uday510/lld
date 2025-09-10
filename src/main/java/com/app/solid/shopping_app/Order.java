
// Liskov Substitution Principle (LSP)

public class Order {
    private ShoppingCart cart;
    private PaymentMethod payment;

    public Order(ShoppingCart cart, PaymentMethod payment) {
        this.cart = cart;
        this.payment = payment;
    }

    public void checkout() {
        double total = cart.calculateTotal();
        payment.pay(total);
    }
}
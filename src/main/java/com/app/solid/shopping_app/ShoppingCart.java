import java.util.List;

// SRP
public class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public ShoppingApp() { }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(product::getPrice).sum();
    }
}
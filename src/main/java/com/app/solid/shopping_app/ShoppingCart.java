package com.app.solid.shopping_app;

import com.app.solid.shopping_app.Product;

import java.util.ArrayList;
import java.util.List;

// SRP
class ShoppingCart {
    private List<Product> items = new ArrayList<>();

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
//        return items.stream().mapToDouble(product:::items).
        return 0;
    }
}
package com.app.pblms.vending_machine.services;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();

    public void add(Product product, int count) {
        products.put(product.getCode(), product);
        stock.merge(product.getCode(), count, Integer::sum);
    }

    public Product getProduct(String code) {
        Product product = products.get(code);
        if (product == null) {
            throw new IllegalArgumentException("Invalid code: " + code);
        }
        return product;
    }

    public boolean isInStock(String code) {
        return stock.getOrDefault(code, 0) > 0;
    }

    public void reduceStock(String code) {
        stock.merge(code, -1, Integer::sum);
    }

    public int getStock(String code) {
        return stock.getOrDefault(code, 0);
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public Map<String, Integer> getStock() {
        return stock;
    }
}

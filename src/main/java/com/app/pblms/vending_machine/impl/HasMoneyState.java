package com.app.pblms.vending_machine.impl;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.interfaces.State;
import com.app.pblms.vending_machine.services.Product;
import com.app.pblms.vending_machine.services.VendingMachine;

public class HasMoneyState implements State {

    @Override
    public State insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.addBalance(denomination);
        System.out.println("  Inserted " + denomination.getValue() + " | balance = " + vendingMachine.getBalance());
        return this;
    }

    @Override
    public State selectProduct(VendingMachine vendingMachine, String code) {
        Product product = vendingMachine.getInventory().getProduct(code);

        if (!vendingMachine.getInventory().isInStock(code)) {
            throw new IllegalStateException("Out of stock: " + product.getName());
        }

        if (vendingMachine.getBalance() < product.getPrice()) {
            throw new IllegalStateException(
                    "Insufficient funds: need " + product.getPrice() + ", have " + vendingMachine.getBalance());
        }

        vendingMachine.setSelected(product);
        System.out.println("  Selected " + product.getName() + " (" + product.getPrice() + ")");
        return new DispensingState();
    }

    @Override
    public State cancel(VendingMachine vendingMachine) {
       vendingMachine.refund();
       return new IdleState();
    }

    @Override
    public State dispense(VendingMachine vendingMachine) {
        throw new IllegalStateException("Select a product first");
    }
}

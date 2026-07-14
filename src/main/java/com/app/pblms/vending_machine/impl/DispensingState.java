package com.app.pblms.vending_machine.impl;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.interfaces.State;
import com.app.pblms.vending_machine.services.Product;
import com.app.pblms.vending_machine.services.VendingMachine;

import java.util.Map;

public class DispensingState implements State {

    @Override
    public State insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        throw new IllegalStateException("Busy dispensing");
    }

    @Override
    public State selectProduct(VendingMachine vendingMachine, String code) {
        throw new IllegalStateException("Busy dispensing");
    }

    @Override
    public State cancel(VendingMachine vendingMachine) {
        throw new IllegalStateException("Cannot cancel mid-dispense");
    }

    @Override
    public State dispense(VendingMachine vendingMachine) {
        Product product = vendingMachine.getSelected();
        int change = vendingMachine.getBalance() - product.getPrice();

        Map<Denomination, Integer> coins = vendingMachine.getCashBox().computeChange(change);
        if (change > 0 && coins == null) {
            vendingMachine.refund();
            return new IdleState();
        }

        vendingMachine.getInventory().reduceStock(product.getCode());
        vendingMachine.vend(product);
        if (change > 0) {
            vendingMachine.getCashBox().dispense(coins);
            vendingMachine.returnChange(change);
        }

        vendingMachine.reset();
        return new IdleState();
    }
}

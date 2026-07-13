package com.app.pblms.vending_machine.impl;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.interfaces.State;
import com.app.pblms.vending_machine.services.VendingMachine;

public class IdleState implements State {

    @Override
    public State insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.addBalance(denomination);
        System.out.println("  Inserted " + denomination.getValue() + " | balance = " + vendingMachine.getBalance());
        return new HasMoneyState();
    }

    @Override
    public State selectProduct(VendingMachine vendingMachine, String code) {
        throw new IllegalStateException("Insert money first");
    }

    @Override
    public State cancel(VendingMachine vendingMachine) {
        return this;
    }

    @Override
    public State dispense(VendingMachine vendingMachine) {
        throw new IllegalStateException("Nothing to dispense");
    }

}

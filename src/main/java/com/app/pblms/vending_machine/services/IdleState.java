package com.app.pblms.vending_machine.services;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.impl.HasMoneyState;
import com.app.pblms.vending_machine.interfaces.State;

public class IdleState implements State {

    public State insertMoney(VendingMachine vendingMachine, Denomination denomination) {
        vendingMachine.addBalance(denomination);
        System.out.println("  Inserted " + denomination.getValue() + " | balance = " + vendingMachine.getBalance());
        return new HasMoneyState();
    }

    public State selectProduct(VendingMachine vendingMachine, String code) {
        throw new IllegalStateException("Insert money first");
    }

    public State cancel(VendingMachine vendingMachine) {
        return this;
    }

    public State dispense(VendingMachine vendingMachine) {
        throw new IllegalStateException("Noting to dispense");
    }

}

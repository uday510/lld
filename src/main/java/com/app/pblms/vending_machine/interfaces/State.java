package com.app.pblms.vending_machine.interfaces;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.services.VendingMachine;

public interface State {
    State insertMoney(VendingMachine vendingMachine, Denomination denomination);
    State selectProduct(VendingMachine vendingMachine, String code);
    State cancel(VendingMachine vendingMachine);
    State dispense(VendingMachine vendingMachine);
}

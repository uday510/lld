package com.app.pblms.vending_machine.services;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.interfaces.State;

public class VendingMachine {

    private State state = new IdleState();
    private final Inventory inventory = new Inventory();
    private final CashBox cashBox = new CashBox();
    private int balance = 0;
    private Product selected;

    public void insertMoney(Denomination denomination) {
        state = state.insertMoney(this, denomination);
    }

    public void selectProduct(String code) {
        state = state.selectProduct(this, code);
        state = state.dispense(this);
    }

    public void cancel() {
        state = state.cancel(this);
    }

    public void addBalance(Denomination denomination) {
        balance += denomination.getValue();
        cashBox.addOne(denomination);
    }
    public int getBalance()                     { return balance;     }
    public void setSelected(Product product)    { selected = product; }
    public Product getSelected()                { return selected;    }
    public Inventory getInventory()             { return inventory;   }
    public CashBox getCashBox()                 { return cashBox;     }

    public void vend(Product product)           {  System.out.println(" >>> Dispensing: " + product.getName()); }
    public void returnChange(int amount)        {  System.out.println(" >>> Change returned: " + amount);       }
    public void reset()                         {  balance = 0; selected = null;                                }
    public void refund() {
        System.out.println(" >>> Refunded: " + balance);
        reset();
    }

    public String currentState()                {  return state.getClass().getSimpleName();                     }
}

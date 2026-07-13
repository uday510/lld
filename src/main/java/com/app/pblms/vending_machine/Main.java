package com.app.pblms.vending_machine;

import com.app.pblms.vending_machine.enums.Denomination;
import com.app.pblms.vending_machine.services.Product;
import com.app.pblms.vending_machine.services.VendingMachine;

public class Main {

    static void main() throws Exception {

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.getInventory().add(new Product("A1", "Coke", 25), 2);
        vendingMachine.getInventory().add(new Product("A2", "Water", 20), 1);
        vendingMachine.getInventory().add(new Product("A3", "Chips", 30), 0);

        vendingMachine.getCashBox().add(Denomination.ONE, 10);
        vendingMachine.getCashBox().add(Denomination.TWO, 10);
        vendingMachine.getCashBox().add(Denomination.FIVE, 10);
        vendingMachine.getCashBox().add(Denomination.TEN, 5);

        System.out.println("=== 1. Happy path: buy Coke (25) with 30 ===");
        vendingMachine.insertMoney(Denomination.TWENTY);
        vendingMachine.insertMoney(Denomination.TEN);
        vendingMachine.selectProduct("A1");
        System.out.println("  state = " + vendingMachine.currentState());

        System.out.println("\n=== 2. Select with no money (invalid in IDLE) ===");
        try {
            vendingMachine.selectProduct("A1");
        } catch (IllegalStateException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }

        System.out.println("\n=== 3. Insufficient funds ===");
        vendingMachine.insertMoney(Denomination.TEN);
        try {
            vendingMachine.selectProduct("A1");
        } catch (IllegalStateException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }

        System.out.println("\n=== 4. Cancel -> refund ===");
        vendingMachine.cancel();
        System.out.println("  state = " + vendingMachine.currentState());

        System.out.println("\n=== 5. Out of stock ===");
        vendingMachine.insertMoney(Denomination.FIFTY);

        try {
            vendingMachine.selectProduct("A3");
        } catch (IllegalStateException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }
        vendingMachine.cancel();

        System.out.println("\n=== 6. Invalid product code ===");
        vendingMachine.insertMoney(Denomination.FIFTY);
        try {
            vendingMachine.selectProduct("Z9");
        } catch (IllegalArgumentException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }
        vendingMachine.cancel();

        System.out.println("\n=== 7. Exact change (no change returned) ===");
        vendingMachine.insertMoney(Denomination.TWENTY);
        vendingMachine.selectProduct("A2");                     // Water = 20, exact
        System.out.println("  state = " + vendingMachine.currentState());

        System.out.println("\n=== 8. Buy Coke with 50 (change = 25) ===");
        vendingMachine.insertMoney(Denomination.FIFTY);
        try {
            vendingMachine.selectProduct("A1");                  // Coke = 25, change = 25
        } catch (RuntimeException e) {
            System.out.println("  Rejected: " + e.getMessage());
        }
        System.out.println("  state = " + vendingMachine.currentState());

    }
}

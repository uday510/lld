package com.app.pblms.splitwise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ExpenseManager {

    private final List<Expense> expenses = new ArrayList<>();
    // balances[A][B] = how much A owes B (positive means A owes; negative means A is owed)
    private final Map<User, Map<User, BigDecimal>> balances = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();


    public void addExpense(Expense expense) {
        if (!expense.isValid()) {
            throw new IllegalArgumentException(
                    "Splits don't sum total: " + expense.getTotalAmount()
            );
        }
        lock.lock();
        try {
            expenses.add(expense);

        } finally {
            lock.unlock();
        }
    }

    private void updateBalance(Expense expense) {
        User payer = expense.getPaidBy();
        for (Split split : expense.getSplits()) {
            User ower = split.getUser();
            if (ower.equals(payer)) continue;


        }
    }
}

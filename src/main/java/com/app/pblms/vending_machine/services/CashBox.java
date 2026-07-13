package com.app.pblms.vending_machine.services;

import com.app.pblms.vending_machine.enums.Denomination;

import java.util.*;

public class CashBox {

    private final Map<Denomination, Integer> cash = new EnumMap<>(Denomination.class);

    public void add(Denomination denomination, int count) {
        cash.merge(denomination, count, Integer::sum);
    }

    public void addOne(Denomination denomination) {
        cash.merge(denomination, 1, Integer::sum);
    }

    public Map<Denomination, Integer> computeChange(int amount) {
        Map<Denomination, Integer> result = new EnumMap<>(Denomination.class);
        int remaining = amount;

        List<Denomination> desc = Arrays.stream(Denomination.values())
                .sorted(Comparator.comparingInt(Denomination::getValue).reversed())
                .toList();

        for (Denomination denomination : desc) {
            int available = cash.getOrDefault(denomination, 0);
            int needed = remaining / denomination.getValue();
            int use = Math.min(needed, available);

            if (use > 0) {
                result.put(denomination, use);
                remaining -= use * denomination.getValue();
            }
        }

        return remaining == 0 ? result : null;
    }

    public void dispense(Map<Denomination, Integer> change) {
        change.forEach(((denomination, count) -> cash.merge(denomination, -count, Integer::sum)));
    }
}

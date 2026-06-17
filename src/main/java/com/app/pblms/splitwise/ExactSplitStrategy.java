package com.app.pblms.splitwise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> split(BigDecimal total, List<User> users, List<BigDecimal> exactAmounts) {
        if (users.size() != exactAmounts.size()) {
            throw new IllegalArgumentException("users and amounts size mismatch");
        }

        BigDecimal sum = exactAmounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sum.compareTo(total) != 0) {
            throw new IllegalArgumentException(
              "Exact amounts sum to " + sum + ", expected " + total
            );
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            splits.add(new Split(users.get(i), exactAmounts.get(i)));
        }

        return splits;
    }
}

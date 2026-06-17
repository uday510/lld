package com.app.pblms.splitwise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    @Override
    public List<Split> split(BigDecimal total, List<User> users, List<BigDecimal> percentages) {
        if (users.size() != percentages.size()) {
            throw new IllegalArgumentException("users and percentage size mismatch");
        }

        BigDecimal sumPct = percentages.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sumPct.compareTo(HUNDRED) != 0) {
            throw new IllegalArgumentException("Percentages sum to " + sumPct + ", expected 100");
        }

        List<Split> splits = new ArrayList<>();
        BigDecimal allocated = BigDecimal.ZERO;
        for (int i = 0; i < users.size() - 1; i++) {
            BigDecimal share = total.multiply(percentages.get(i))
                    .divide(HUNDRED, 2, RoundingMode.HALF_UP);
            splits.add(new Split(users.get(i), share));
            allocated = allocated.add(share);
        }

        // Last absorbs rounding
        splits.add(new Split(users.get(users.size() - 1), total.subtract(allocated)));
        return splits;
    }
}

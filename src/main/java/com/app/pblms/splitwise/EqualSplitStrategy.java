package com.app.pblms.splitwise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> split(BigDecimal total, List<User> users, List<BigDecimal> params) {
        int n = users.size();
        if (n == 0) throw new IllegalArgumentException("Need at least one user");

        BigDecimal each = total.divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);

        List<Split> splits = new ArrayList<>();
        BigDecimal allocated = BigDecimal.ZERO;
        for (int i = 0; i < n - 1; i++) {
            splits.add(new Split(users.get(i), each));
            allocated = allocated.add(each);
        }

        // Last user absorbs the rounding remainder
        splits.add(new Split(users.get(n - 1), total.subtract(allocated)));
        return splits;
    }

}

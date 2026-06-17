package com.app.pblms.splitwise;

import java.math.BigDecimal;
import java.util.List;

public interface SplitStrategy {
    List<Split> split(BigDecimal total, List<User> users, List<BigDecimal> params);
}

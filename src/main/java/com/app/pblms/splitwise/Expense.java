package com.app.pblms.splitwise;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Expense {
    private final String id;
    private final String description;
    private final User paidBy;
    private final BigDecimal totalAmount;
    private final List<Split> splits;
    private final Instant createdAt;

    public Expense(String description, BigDecimal totalAmount,
                   User paidBy, List<Split> splits
                   ) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.splits = List.copyOf(splits);      // defensive immutable copy
        this.createdAt = Instant.now();
    }

    public boolean isValid() {
        BigDecimal sum = splits.stream()
                .map(Split::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.compareTo(totalAmount) == 0;
    }

    public String getId()                   { return id; }
    public String getDescription()          { return description; }
    public BigDecimal getTotalAmount()      { return totalAmount; }
    public User getPaidBy()                 { return paidBy; }
    public List<Split> getSplits()          { return splits; }
    public Instant getCreatedAt()           { return createdAt; }
}

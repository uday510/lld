package com.app.patterns.creational.factory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Supplier;

interface PaymentStrategy {
    BigDecimal process(BigDecimal amount);
}

class CreditCard implements PaymentStrategy {

    @Override
    public BigDecimal process(BigDecimal amount) {
        return BigDecimal.ONE;
    }

}

class DebitCard implements PaymentStrategy {

    @Override
    public BigDecimal process(BigDecimal amount) {
        return BigDecimal.TEN;
    }

}

class UPI implements PaymentStrategy {

    @Override
    public BigDecimal process(BigDecimal amount) {
        return BigDecimal.TWO;
    }

}

interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("Console Logger");
    }

}

class FilerLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("Writing to log file");
    }

}

class CloudLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("cloud message");
    }

}


class Solution {

    private static final Map<String, Supplier<PaymentStrategy>> registry = Map.of(
            "CARD", CreditCard::new,
            "DEBIT", DebitCard::new,
            "UPI", UPI::new
    );

    static void main() {

        Supplier<PaymentStrategy> supplier = registry.get("CARD");

        supplier.get();
    }

}

package io.github.hyungjun.cakego.domain;

import java.math.BigDecimal;

public class Money {
    private static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money won(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money zero() {
        return ZERO;
    }

    public Money plus(Money other) {
        return new Money(this.amount.add(other.amount));
    }
}

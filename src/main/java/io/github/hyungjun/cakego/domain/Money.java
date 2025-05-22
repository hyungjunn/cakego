package io.github.hyungjun.cakego.domain;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money money)) return false;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}

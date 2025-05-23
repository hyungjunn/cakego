package io.github.hyungjun.cakego.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void returnsSumOfTwoMoneyInstances() {
        Money basePrice = Money.of(10000);
        Money adjustment = Money.of(2000);
        assertThat(basePrice.plus(adjustment)).isEqualTo(Money.of(12000));
    }
}

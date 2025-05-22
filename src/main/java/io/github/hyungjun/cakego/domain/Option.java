package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Option {
    private String name;
    private Money money;

    public Option(String name, Money money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Option)) return false;
        Option option = (Option) o;
        return Objects.equals(name, option.name) && Objects.equals(money, option.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }
}

package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Option {
    private String name;
    private Money money;
    private OptionGroup optionGroup;

    public Option(String name, Money money, OptionGroup optionGroup) {
        this.name = name;
        this.money = money;
        this.optionGroup = optionGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Option option)) return false;
        return Objects.equals(name, option.name) &&
               Objects.equals(money, option.money) &&
               Objects.equals(optionGroup, option.optionGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, optionGroup);
    }

    public boolean isValidGroupInTemplate(CakeTemplate cakeTemplate) {
        if (optionGroup == null) {
            return false;
        }
        return cakeTemplate.contains(optionGroup);
    }
}

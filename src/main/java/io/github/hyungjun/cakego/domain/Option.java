package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Option {
    private Long id;
    private String name;
    private Money priceAdjustment;
    private OptionGroup optionGroup;
    private boolean isAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Option option)) return false;
        return Objects.equals(name, option.name) &&
               Objects.equals(priceAdjustment, option.priceAdjustment) &&
               Objects.equals(optionGroup, option.optionGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priceAdjustment, optionGroup);
    }
}

package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class OptionGroup {
    private String name;

    public OptionGroup(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OptionGroup that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

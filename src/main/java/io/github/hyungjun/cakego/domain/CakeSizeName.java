package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class CakeSizeName {
    private final String name;

    public CakeSizeName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CakeSizeName that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

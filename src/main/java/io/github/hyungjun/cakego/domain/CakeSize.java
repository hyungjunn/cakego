package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class CakeSize {
    private final String size;

    public CakeSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CakeSize that)) return false;
        return Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size);
    }
}

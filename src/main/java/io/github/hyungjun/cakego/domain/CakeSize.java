package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class CakeSize {
    public static final int MAX_CAKE_SIZE_LENGTH = 20;
    private final String size;

    public CakeSize(String size) {
        this.size = validateCakeSize(size);
    }

    private String validateCakeSize(String size) {
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("사이즈는 null이거나 공백일 수 없습니다.");
        }

        String trimmedSize = size.trim();
        if (trimmedSize.length() > MAX_CAKE_SIZE_LENGTH) {
            throw new IllegalArgumentException(String.format("케이크 사이즈는 %d자를 초과할 수 없습니다.", MAX_CAKE_SIZE_LENGTH));
        }
        return trimmedSize;
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

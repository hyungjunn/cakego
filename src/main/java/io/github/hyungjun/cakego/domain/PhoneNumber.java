package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class PhoneNumber {
    private final static String PHONE_REGEX = "\\d{3}-\\d{3,4}-\\d{4}";

    private final String value;

    public PhoneNumber(String value) {
        validateNullOrBlank(value);
        validatePhoneRegex(value);
        this.value = value;
    }

    private static void validatePhoneRegex(String value) {
        if (!value.matches(PHONE_REGEX)) {
            throw new IllegalArgumentException("전화번호는 xxx-xxxx-xxxx 형식이어야 합니다.");
        }
    }

    private static void validateNullOrBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("번호는 null이거나 공백일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhoneNumber that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class Customer {
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 50;
    private String name;
    private PhoneNumber phoneNumber;

    public Customer(String name, PhoneNumber phoneNumber) {
        this.name = validateName(name);
        this.phoneNumber = validatePhoneNumber(phoneNumber);;
    }

    private PhoneNumber validatePhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("전화번호는 필수입니다.");
        }
        return phoneNumber;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 null이거나 공백일 수 없습니다.");
        }
        String trimmedName = name.trim();
        if (trimmedName.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 최소 %d자 이상이어야 합니다.", MIN_NAME_LENGTH));
        }
        if (trimmedName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름은 %d자를 초과할 수 없습니다.", MAX_NAME_LENGTH));
        }
        return trimmedName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(name, customer.name) &&
               Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }
}

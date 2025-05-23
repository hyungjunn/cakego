package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class Customer { // VO
    private String name;
    private PhoneNumber phoneNumber;

    public Customer(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
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

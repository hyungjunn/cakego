package io.github.hyungjun.cakego.domain;

public class Customer {
    private String name;
    private PhoneNumber phoneNumber;

    public Customer(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

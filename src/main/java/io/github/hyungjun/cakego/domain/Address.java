package io.github.hyungjun.cakego.domain;

import java.util.Objects;

public class Address { // VO
    private String postalCode;
    private String baseAddress;
    private String detailAddress;

    public Address(String postalCode, String baseAddress, String detailAddress) {
        validatePostalCode(postalCode);
        validateBaseAddress(baseAddress);
        this.postalCode = postalCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
    }

    public static Address of(String postalCode, String baseAddress, String detailAddress) {
        return new Address(postalCode, baseAddress, detailAddress);
    }

    private static void validateBaseAddress(String baseAddress) {
        if (baseAddress == null || baseAddress.isBlank()) {
            throw new IllegalArgumentException("기본 주소는 필수입니다");
        }
        if (baseAddress.length() < 5) {
            throw new IllegalArgumentException("기본 주소는 최소 5자 이상이어야 합니다");
        }
    }

    private static void validatePostalCode(String postalCode) {
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("우편번호는 필수입니다");
        }
        if (!postalCode.matches("\\d{5}")) {
            throw new IllegalArgumentException("우편번호는 5자리 숫자여야 합니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Address address)) return false;
        return Objects.equals(postalCode, address.postalCode) &&
               Objects.equals(baseAddress, address.baseAddress) &&
               Objects.equals(detailAddress, address.detailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, baseAddress, detailAddress);
    }
}

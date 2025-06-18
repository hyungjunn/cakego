package io.github.hyungjun.cakego.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class AddressTest {
    @Test
    void addressCreationSuccess() {
        Address address = Address.of("12345", "서울시 강남구 테헤란로 123", "456호");
        assertThat(address).isNotNull();
    }

    @ParameterizedTest(name = "[{index}] invalid (null or empty): \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void zipCodeIsRequired(String zipCode) {
        assertThatThrownBy(() -> Address.of(zipCode, "서울시 강남구", "123호"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("우편번호는 필수입니다");
    }

    @ParameterizedTest(name = "[{index}] malformedZipCode: \"{0}\"")
    @ValueSource(strings = {"1234", "123456", "12a45", "12-45"})
    void zipCodeMustBeExactly5Digits(String zipCode) {
        assertThatThrownBy(() -> Address.of(zipCode, "서울시 강남구", "123호"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("우편번호는 5자리 숫자여야 합니다");
    }

    @ParameterizedTest(name = "[{index}] invalidMainAddress (null or blank): \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void mainAddressIsRequired(String mainAddress) {
        assertThatThrownBy(() -> Address.of("12345", mainAddress, "123호"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기본 주소는 필수입니다");
    }

    @ParameterizedTest(name = "[{index}] tooShortMainAddress: \"{0}\"")
    @ValueSource(strings = {"서울", "1234", "가나"})
    void mainAddressMustBeAtLeast5Characters(String mainAddress) {
        assertThatThrownBy(() -> Address.of("12345", mainAddress, "123호"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기본 주소는 최소 5자 이상이어야 합니다");
    }

    @ParameterizedTest(name = "[{index}] optionalDetailAddress: \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = { "   " })
    void allowOptionalDetailAddress(String detailAddress) {
        assertThatCode(() -> Address.of("12345", "서울시 강남구 테헤란로", detailAddress))
                .doesNotThrowAnyException();
    }

    @Test
    void checkAddressEquality() {
        Address address1 = Address.of("12345", "서울시 강남구", "123호");
        Address address2 = Address.of("12345", "서울시 강남구", "123호");
        Address address3 = Address.of("54321", "부산시 해운대구", "456호");
        Address address4 = Address.of("12345", "서울시 강남구", "789호"); // 상세주소 다름
        assertThat(address1)
                .isEqualTo(address2)
                .isNotEqualTo(address3)
                .isNotEqualTo(address4)
                .hasSameHashCodeAs(address2);
        assertThat(address1.hashCode())
                .isNotEqualTo(address3.hashCode())
                .isNotEqualTo(address4.hashCode());
    }

    @Test
    void boundaryValueTest() {
        assertThatCode(() -> Address.of("00000", "서울시 강남구 테헤란로", "1호"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Address.of("99999", "부산광역시 해운대구 해운대해변로", null))
                .doesNotThrowAnyException();
        assertThatCode(() -> Address.of("12345", "서울시 강남구", "123호"))
                .doesNotThrowAnyException();
    }
}

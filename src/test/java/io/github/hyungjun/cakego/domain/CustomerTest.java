package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CustomerTest {

    @ParameterizedTest
    @NullAndEmptySource
    void test_customer_name_is_null_or_blank(String input) {
        PhoneNumber phoneNumber = new PhoneNumber("010-0000-0000");
        Assertions.assertThatThrownBy(() -> new Customer(input, phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 null이거나 공백일 수 없습니다.");
    }

    @Test
    void exception_when_phone_number_is_null() {
        Assertions.assertThatThrownBy(() -> new Customer("홍길동", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("전화번호는 필수입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"홍", "길", "동", "동 "})
    void exception_when_name_is_too_short(String name) {
        PhoneNumber phoneNumber = new PhoneNumber("010-0000-0000");
        Assertions.assertThatThrownBy(() -> new Customer(name, phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 최소 2자 이상이어야 합니다.");;
    }

    @Test
    void exception_when_name_is_too_long() {
        String longName = "가".repeat(51);
        PhoneNumber phoneNumber = new PhoneNumber("010-0000-0000");
        Assertions.assertThatThrownBy(() -> new Customer(longName, phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 50자를 초과할 수 없습니다.");
    }

    @Test
    void trim_name_before_validation() {
        String nameWithSpaces = "   홍길동   ";
        PhoneNumber phoneNumber = new PhoneNumber("010-0000-0000");
        Customer customer = new Customer(nameWithSpaces, phoneNumber);
        Assertions.assertThat(customer).isEqualTo(new Customer("홍길동", phoneNumber));
    }
}

package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class CustomerTest {

    @ParameterizedTest
    @NullAndEmptySource
    void test_customer_name_is_null_or_blank(String input) {
        PhoneNumber phoneNumber = new PhoneNumber("010-0000-0000");
        Assertions.assertThatThrownBy(() -> new Customer(input, phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 null이거나 공백일 수 없습니다.");
    }
}

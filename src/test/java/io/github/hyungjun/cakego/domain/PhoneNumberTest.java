package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PhoneNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "0103-1234-5678",
            "010-1234-abcd",
            "01012345678",
            "01-1234-5678",
            "010-12345-6789",
            "010-1234-567",
            "010-1234-56789",
            " 010-1234-5678",
            "010-1234-5678 ",
            "010--1234-5678",
            "010_1234_5678",
            "+82-10-1234-5678",
    })
    void name(String phoneNumber) {
        Assertions.assertThatThrownBy(() -> new PhoneNumber(phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("전화번호는 xxx-xxxx-xxxx 형식이어야 합니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenPhoneNumberIsNullOrBlank(String phoneNumber) {
        Assertions.assertThatThrownBy(() -> new PhoneNumber(phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("번호는 null이거나 공백일 수 없습니다.");
    }
}

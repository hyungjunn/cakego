package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class CakeSizeTest {
    @ParameterizedTest
    @NullAndEmptySource
    void exception_when_size_is_null_or_blank(String size) {
        Assertions.assertThatThrownBy(() -> new CakeSize(size))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사이즈는 null이거나 공백일 수 없습니다.");
    }

    @Test
    void trim_size_before_storing() {
        String sizeWithSpaces = "   1호   ";
        CakeSize cakeSize = new CakeSize(sizeWithSpaces);
        Assertions.assertThat(cakeSize).isEqualTo(new CakeSize("1호"));
    }

    @Test
    void exception_when_size_is_too_long() {
        String longSize = "a".repeat(21);
        Assertions.assertThatThrownBy(() -> new CakeSize(longSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("케이크 사이즈는 20자를 초과할 수 없습니다.");
    }
}

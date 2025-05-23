package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class OptionGroup {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OptionGroup that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

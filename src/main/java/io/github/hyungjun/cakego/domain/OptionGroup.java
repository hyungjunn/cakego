package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OptionGroup {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

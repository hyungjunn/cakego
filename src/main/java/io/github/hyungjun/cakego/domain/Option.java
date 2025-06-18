package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Option {
    private Long id;
    private String name;
    private Money priceAdjustment;
    private OptionGroup optionGroup;
    private boolean isAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package io.github.hyungjun.cakego.domain;

import java.time.LocalDateTime;

public class CakeTemplateOptionGroup { // 중간 테이블
    private Long id;
    private CakeTemplate cakeTemplate;
    private OptionGroup optionGroup;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

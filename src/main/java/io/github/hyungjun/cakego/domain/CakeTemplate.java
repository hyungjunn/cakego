package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CakeTemplate {
    private Long id;
    private String name;
    private String description;
    private Shop shop;
    private CakeSize size;
    private CakeOrderType cakeType;
    private Money basePrice;
    private boolean isAvailable;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

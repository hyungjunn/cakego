package io.github.hyungjun.cakego.domain;

import java.time.LocalDateTime;

public class Shop {
    private Long id;
    private ShopOwner shopOwner;
    private String name;
    private Address address;
    private String phoneNumber;
    private String description;
    private String businessRegistrationNumber;
    private String shopImageUrl;
    private String openingHours;
    private boolean isActive; // 기본값 true, nullable = false, shop을 삭제할 때 isActive만 false로 한 다음 저장시키기, @where
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

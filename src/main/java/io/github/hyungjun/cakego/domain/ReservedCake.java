package io.github.hyungjun.cakego.domain;

import java.time.LocalDateTime;

public class ReservedCake {
    private Long id;
    private CakeTemplate cakeTemplate;
    private Reservation reservation;
    private Money appliedPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package io.github.hyungjun.cakego.domain;

import java.time.LocalDateTime;

public class ReservedCakeOption { // 중간 테이블
    private Long id;
    private ReservedCake reservedCake;
    private Option option;
    private String optionNameAtBooking; // 예약 시점의 옵션 이름
    private Money optionPriceAtBooking; // 예약 시점의 옵션 가격
    private LocalDateTime createdAt;
}

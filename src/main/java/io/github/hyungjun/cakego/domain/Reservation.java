package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Reservation {
    private Long id;
    private Shop shop;
    private Customer customer;
    private String reservationNumber;
    private LocalDateTime pickUpDateTime;
    private ReservationStatus status;
    private Money totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

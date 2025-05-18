package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Reservation {
    private CakeSize cakeSize;
    private Customer customer;
    private List<Option> selectedOptions;
    private LocalDateTime pickupTime;
    private ReservationStatus status;

    public Reservation(CakeSize cakeSize, Customer customer, List<Option> selectedOptions, LocalDateTime pickupTime) {
        if (cakeSize.isHidden()) {
            throw new IllegalArgumentException("예약할 수 없는 케이크입니다.");
        }
        this.cakeSize = cakeSize;
        this.customer = customer;
        this.selectedOptions = selectedOptions;
        this.pickupTime = pickupTime;
        this.status = ReservationStatus.PENDING;
    }
}

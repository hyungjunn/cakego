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
        validateOptions(cakeSize, selectedOptions);
        if (pickupTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("예약일자는 현재 시간보다 미래여야 합니다.");
        }
        if (isNotReservationUnit(pickupTime)) {
            throw new IllegalArgumentException("예약일자는 30분 단위로 예약 가능합니다.");
        }
        this.cakeSize = cakeSize;
        this.customer = customer;
        this.selectedOptions = selectedOptions;
        this.pickupTime = pickupTime;
        this.status = ReservationStatus.PENDING;
    }

    private static void validateOptions(CakeSize cakeSize, List<Option> selectedOptions) {
        List<String> invalidOptionNames = cakeSize.findInvalidOptionNames(selectedOptions);
        if (!invalidOptionNames.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 옵션입니다: " + String.join(", ", invalidOptionNames));
        }
    }

    private boolean isNotReservationUnit(LocalDateTime pickupTime) {
        int minute = pickupTime.getMinute();
        return !(minute == 0 || minute == 30);
    }
}

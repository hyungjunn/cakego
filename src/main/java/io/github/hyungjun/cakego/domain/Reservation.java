package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    private Reservation(ReservationBuilder builder) {
        this(builder, Clock.systemDefaultZone());
    }

    Reservation(ReservationBuilder builder, Clock clock) {
        LocalDateTime now = LocalDateTime.now(clock);
        if (builder.pickUpDateTime.isBefore(now)) {
            throw new IllegalArgumentException("예약 날짜, 시간은 현재 시간보다 미래여야 합니다.");
        }
        if (builder.pickUpDateTime.isBefore(now.plusDays(2))) {
            throw new IllegalArgumentException("예약은 최소 2일 후부터 가능합니다.");
        }
        this.shop = builder.shop;
        this.customer = builder.customer;
        this.pickUpDateTime = builder.pickUpDateTime;
        // 기본값
        this.status = ReservationStatus.PENDING;
        this.reservationNumber = generateReservationNumber(); // TODO: 예약번호 생성 로직
        this.totalPrice = Money.ZERO; // TODO: 총 가격 계산 로직
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private String generateReservationNumber() {
        // 임시 구현 (나중에 도메인 서비스로 분리)
        return "RES-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
               + "-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    public static class ReservationBuilder {
        private LocalDateTime pickUpDateTime;
        private Shop shop;
        private Customer customer;

        public ReservationBuilder pickUpDateTime(LocalDateTime pickUpDateTime) {
            this.pickUpDateTime = pickUpDateTime;
            return this;
        }

        public ReservationBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public ReservationBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }
}

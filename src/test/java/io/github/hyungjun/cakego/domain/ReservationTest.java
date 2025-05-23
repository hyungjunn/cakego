package io.github.hyungjun.cakego.domain;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationTest {
    @Test
    void shouldThrowExceptionWhenPickUpDateTimeIsInPast() {
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createReservationWith(pastDateTime)
        );
        assertThat(exception.getMessage()).isEqualTo("예약 날짜, 시간은 현재 시간보다 미래여야 합니다.");
    }

    @Test
    void shouldThrowExceptionWhenPickUpDateTimeIsToday() {
        Clock fixedClock = Clock.fixed(
                Instant.parse("2023-10-01T00:00:00Z"),
                ZoneId.of("UTC")
        );
        LocalDateTime todayDateTime = LocalDateTime.now(fixedClock);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createReservationWith(todayDateTime, fixedClock)
        );
        assertThat(exception.getMessage()).isEqualTo("예약은 최소 2일 후부터 가능합니다.");
    }

    private static void createReservationWith(LocalDateTime pickUpDateTime, Clock clock) {
        new Reservation(Reservation.builder()
                .pickUpDateTime(pickUpDateTime)
                .shop(new Shop())
                .customer(new Customer("홍길동", new PhoneNumber("010-1234-5678"))),
                clock);

    }

    private static void createReservationWith(LocalDateTime pastDateTime) {
        Reservation.builder()
                .pickUpDateTime(pastDateTime)
                .shop(new Shop())
                .customer(new Customer("홍길동", new PhoneNumber("010-1234-5678")))
                .build();
    }
}

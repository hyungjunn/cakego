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
    void exception_when_pickUpDateTime_is_in_past() {
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(1);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> aReservationBuilder().pickUpDateTime(pastDateTime).build()
        );
        assertThat(exception.getMessage()).isEqualTo("예약 날짜, 시간은 현재 시간보다 미래여야 합니다.");
    }

    @Test
    void exception_when_pickUpDateTime_is_today() {
        Clock fixedClock = Clock.fixed(
                Instant.parse("2023-10-01T00:00:00Z"),
                ZoneId.of("UTC")
        );
        LocalDateTime todayDateTime = LocalDateTime.now(fixedClock);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Reservation.forTest(
                        aReservationBuilder().pickUpDateTime(todayDateTime),
                        fixedClock)

        );
        assertThat(exception.getMessage()).isEqualTo("예약은 최소 2일 후부터 가능합니다.");
    }

    @Test
    void exception_when_pickUpDateTime_is_not_multiple_of_30minutes() {
        LocalDateTime invalidDateTime = LocalDateTime.now().plusDays(3).withMinute(15);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> aReservationBuilder().pickUpDateTime(invalidDateTime).build()
        );
        assertThat(exception.getMessage()).isEqualTo("픽업 시간은 30분 단위로만 가능합니다 (예: 14:00, 14:30)");
    }

    private Reservation.ReservationBuilder aReservationBuilder() {
        return Reservation.builder()
                .shop(new Shop())
                .customer(new Customer("홍길동", new PhoneNumber("010-1234-5678")));
    }
}

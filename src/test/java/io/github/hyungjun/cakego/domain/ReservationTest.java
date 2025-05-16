package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationTest {
    @Test
    void shouldStartAsPendingWhenReservationIsCreated() {
        CakeSize cakeSize = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> selectedOptions = selectValidOptionForReservation();
        LocalDateTime pickupTime = validPickupTime();
        Reservation reservation = new Reservation(cakeSize, customer, selectedOptions, pickupTime);
        Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.PENDING);
    }

    private static LocalDateTime validPickupTime() {
        return LocalDateTime.now().plusDays(3).withHour(11).withMinute(0);
    }

    private static List<Option> selectValidOptionForReservation() {
        return List.of(
                new Option("초코+생크림", 1000),
                new Option("생일 축하해", 0),
                new Option("하늘색", 0)
        );
    }

    private static Customer someCustomer() {
        return new Customer("홍길동", "010-1234-5678");
    }

    private static CakeSize regularCakeSizeWithAllOptions() {
        return new CakeSize(
                new CakeSizeName("1호"),
                CakeOrderType.REGULAR,
                43000,
                true,
                List.of(
                        new OptionGroup("시트+샌딩", List.of(
                                new Option("초코+생크림", 1000),
                                new Option("딸기+생크림", 1500)
                        )),
                        new OptionGroup("상단 문구", List.of(
                                new Option("생일 축하해", 0),
                                new Option("사랑해", 0)
                        )),
                        new OptionGroup("배경색", List.of(
                                new Option("하늘색", 0),
                                new Option("분홍색", 0)
                        ))
                )
        );
    }
}

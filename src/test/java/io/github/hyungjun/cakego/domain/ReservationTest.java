package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationTest {
    @Test
    void shouldStartAsPendingWhenReservationIsCreated() {
        // 1. 케이크 사이즈 객체 생성
        CakeSize cakeSize = new CakeSize(
                "1호",
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

        // 2. 고객 객체 생성
        Customer customer = new Customer("홍길동", "010-1234-5678");

        // 3. 옵션 선택
        List<Option> selectedOptions = List.of(
                new Option("초코+생크림", 1000),
                new Option("생일 축하해", 0),
                new Option("하늘색", 0)
        );

        // 4. 픽업 날짜 객체 생성
        LocalDateTime pickupTime = LocalDateTime.now().plusDays(3).withHour(11).withMinute(0);

        // 5. 예약 객체 생성
        Reservation reservation = new Reservation(cakeSize, customer, selectedOptions, pickupTime);

        // 6. 예약 상태 확인
        Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.PENDING);
    }
}

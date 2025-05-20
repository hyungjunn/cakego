package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void shouldThrowExceptionWhenTryingToReserveHiddenCake() {
        CakeSize hiddenCake = cakeSizeHidden();
        Customer customer = someCustomer();
        List<Option> options = selectValidOptionForReservation();
        LocalDateTime pickupTime = validPickupTime();
        assertThatThrownBy(() ->
                new Reservation(hiddenCake, customer, options, pickupTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약할 수 없는 케이크입니다.");
    }

    @Test
    void shouldThrowExceptionWhenReservingWithPastDate() {
        LocalDateTime past = LocalDateTime.now().minusDays(1);
        CakeSize cakeSize = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> options = selectValidOptionForReservation();
        assertThatThrownBy(() ->
                new Reservation(cakeSize, customer, options, past))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약일자는 현재 시간보다 미래여야 합니다.");
    }

    @Test
    void shouldThrowExceptionWhenReservingWithInvalidTime() {
        LocalDateTime invalidTime = LocalDateTime.now().plusDays(3).withHour(11).withMinute(15);
        CakeSize cakeSize = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> options = selectValidOptionForReservation();
        assertThatThrownBy(() ->
                new Reservation(cakeSize, customer, options, invalidTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약일자는 30분 단위로 예약 가능합니다.");
    }

    @Test // 옵션 유효성 검증(사용자가 불일치한 옵션을 api로 설정할 위험이 있기 때문에)
    void shouldThrowExceptionWhenReservingWithInvalidOptions() {
        CakeSize cakeSize = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> options = List.of(
                new Option("초코+생크림", Money.won(1000)),
                new Option("존재하지 않는 옵션1", Money.zero()),
                new Option("존재하지 않는 옵션2", Money.zero())
        );
        LocalDateTime pickupTime = validPickupTime();
        assertThatThrownBy(() ->
                new Reservation(cakeSize, customer, options, pickupTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 옵션입니다: 존재하지 않는 옵션1, 존재하지 않는 옵션2");
    }

    // This testFixture is implemented for the purpose of testing reserve hidden cake.
    private CakeSize cakeSizeHidden() {
        return new CakeSize(
                new CakeSizeName("1호"),
                CakeOrderType.REGULAR,
                Money.won(43000),
                false,
                List.of(
                        new OptionGroup("시트+샌딩", List.of(
                                new Option("초코+생크림", Money.won(1000)),
                                new Option("딸기+생크림", Money.won(2000))
                        )),
                        new OptionGroup("상단 문구", List.of(
                                new Option("생일 축하해", Money.zero()),
                                new Option("사랑해", Money.zero())
                        )),
                        new OptionGroup("배경색", List.of(
                                new Option("하늘색", Money.zero()),
                                new Option("분홍색", Money.zero())
                        ))
                )
        );
    }

    private static LocalDateTime validPickupTime() {
        return LocalDateTime.now().plusDays(3).withHour(11).withMinute(0);
    }

    private static List<Option> selectValidOptionForReservation() {
        return List.of(
                new Option("초코+생크림", Money.won(1000)),
                new Option("생일 축하해", Money.zero()),
                new Option("하늘색", Money.zero())
        );
    }

    private static Customer someCustomer() {
        return new Customer("홍길동", new PhoneNumber("010-1234-5678"));
    }

    private static CakeSize regularCakeSizeWithAllOptions() {
        return new CakeSize(
                new CakeSizeName("1호"),
                CakeOrderType.REGULAR,
                Money.won(43000),
                true,
                List.of(
                        new OptionGroup("시트+샌딩", List.of(
                                new Option("초코+생크림", Money.won(1000)),
                                new Option("딸기+생크림", Money.won(2000))
                        )),
                        new OptionGroup("상단 문구", List.of(
                                new Option("생일 축하해", Money.zero()),
                                new Option("사랑해", Money.zero())
                        )),
                        new OptionGroup("배경색", List.of(
                                new Option("하늘색", Money.zero()),
                                new Option("분홍색", Money.zero())
                        ))
                )
        );
    }
}

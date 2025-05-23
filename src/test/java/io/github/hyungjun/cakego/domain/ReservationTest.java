package io.github.hyungjun.cakego.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReservationTest {
    @Test
    void shouldStartAsPendingWhenReservationIsCreated() {
        CakeTemplate cakeTemplate = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> selectedOptions = selectValidOptionForReservation();
        LocalDateTime pickupTime = validPickupTime();
        Reservation reservation = new Reservation(cakeTemplate, customer, pickupTime);
        Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.PENDING);
    }

    @Test
    void shouldThrowExceptionWhenTryingToReserveHiddenCake() {
        CakeTemplate hiddenCake = cakeSizeHidden();
        Customer customer = someCustomer();
        List<Option> options = selectValidOptionForReservation();
        LocalDateTime pickupTime = validPickupTime();
        assertThatThrownBy(() ->
                new Reservation(hiddenCake, customer, pickupTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약할 수 없는 케이크입니다.");
    }

    @Test
    void shouldThrowExceptionWhenReservingWithPastDate() {
        LocalDateTime past = LocalDateTime.now().minusDays(1);
        CakeTemplate cakeTemplate = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> options = selectValidOptionForReservation();
        assertThatThrownBy(() ->
                new Reservation(cakeTemplate, customer, past))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약일자는 현재 시간보다 미래여야 합니다.");
    }

    @Test
    void shouldThrowExceptionWhenReservingWithInvalidTime() {
        LocalDateTime invalidTime = LocalDateTime.now().plusDays(3).withHour(11).withMinute(15);
        CakeTemplate cakeTemplate = regularCakeSizeWithAllOptions();
        Customer customer = someCustomer();
        List<Option> options = selectValidOptionForReservation();
        assertThatThrownBy(() ->
                new Reservation(cakeTemplate, customer, invalidTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("예약일자는 30분 단위로 예약 가능합니다.");
    }

    // @Test // 옵션 유효성 검증(사용자가 불일치한 옵션을 api로 설정할 위험이 있기 때문에)
    // void shouldThrowExceptionWhenReservingWithInvalidOptions() {
    //     CakeTemplate cakeTemplate = regularCakeSizeWithAllOptions();
    //     Customer customer = someCustomer();
    //     List<Option> options = List.of(
    //             new Option("초코+생크림", Money.won(1000), new OptionGroup("시트+샌딩")),
    //             new Option("존재하지 않는 옵션1", Money.zero(), new OptionGroup("상단 문구")),
    //             new Option("존재하지 않는 옵션2", Money.zero(), new OptionGroup("배경색"))
    //     );
    //     LocalDateTime pickupTime = validPickupTime();
    //     assertThatThrownBy(() ->
    //             new Reservation(cakeTemplate, customer, pickupTime))
    //             .isInstanceOf(IllegalArgumentException.class)
    //             .hasMessageContaining("유효하지 않은 옵션입니다: 존재하지 않는 옵션1, 존재하지 않는 옵션2");
    // }

    // This testFixture is implemented for the purpose of testing reserve hidden cake.
    private CakeTemplate cakeSizeHidden() {
        return new CakeTemplate(
                new CakeSize("1호"),
                CakeOrderType.REGULAR,
                Money.won(43000),
                false,
                List.of(
                        new OptionGroup("시트+샌딩"),
                        new OptionGroup("상단 문구"),
                        new OptionGroup("배경색")
                )
        );
    }

    private static LocalDateTime validPickupTime() {
        return LocalDateTime.now().plusDays(3).withHour(11).withMinute(0);
    }

    private static List<Option> selectValidOptionForReservation() {
        return List.of(
                new Option("초코+생크림", Money.won(1000), new OptionGroup("시트+샌딩")),
                new Option("생일 축하해", Money.zero(), new OptionGroup("상단 문구")),
                new Option("하늘색", Money.zero(), new OptionGroup("배경색"))
        );
    }

    private static Customer someCustomer() {
        return new Customer("홍길동", new PhoneNumber("010-1234-5678"));
    }

    private static CakeTemplate regularCakeSizeWithAllOptions() {
        return new CakeTemplate(
                new CakeSize("1호"),
                CakeOrderType.REGULAR,
                Money.won(43000),
                true,
                List.of(
                        new OptionGroup("시트+샌딩"),
                        new OptionGroup("상단 문구"),
                        new OptionGroup("배경색")
                )
        );
    }
}

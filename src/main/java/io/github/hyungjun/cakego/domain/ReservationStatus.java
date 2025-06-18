package io.github.hyungjun.cakego.domain;

public enum ReservationStatus {
    PENDING_PAYMENT, // 입금 대기
    PENDING_APPROVAL, // 승인 대기
    CONFIRMED, // 예약 확정
    REJECTED, // 예약 거절
    CANCELED_REQUESTED, // 취소 요청
    CANCELED,  // 예약 취소 완료
    PICKED_UP  // 픽업 완료
}

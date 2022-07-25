package com.triple.itinerary.domain.trip.exception;

public class WrongDepartureDateException extends IllegalArgumentException {

    public WrongDepartureDateException() {
        super("종료일이 시작일보다 앞섭니다.");
    }
}

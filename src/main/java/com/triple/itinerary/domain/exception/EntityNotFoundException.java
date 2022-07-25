package com.triple.itinerary.domain.exception;

public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(String s) {
        super(s);
    }
}

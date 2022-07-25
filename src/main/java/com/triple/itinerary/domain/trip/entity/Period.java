package com.triple.itinerary.domain.trip.entity;

import com.triple.itinerary.domain.trip.exception.WrongDepartureDateException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.*;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Period {

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    @Builder
    private Period(LocalDate arrivalDate, LocalDate departureDate) {
        if (Objects.nonNull(departureDate) && Objects.nonNull(arrivalDate)
                && departureDate.isAfter(arrivalDate)) {
            throw new WrongDepartureDateException();
        }
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public void updateDate(LocalDate arrivalDate, LocalDate departureDate) {
        if (departureDate.isAfter(arrivalDate)) {
            throw new WrongDepartureDateException();
        }
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }
}

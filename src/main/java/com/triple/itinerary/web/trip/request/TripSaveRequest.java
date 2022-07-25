package com.triple.itinerary.web.trip.request;

import com.triple.itinerary.domain.trip.entity.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
public class TripSaveRequest {

    @NotBlank
    private String city;

    @NotNull
    private LocalDate arrivalDate;

    @NotNull
    private LocalDate departureDate;

    public Trip toEntity() {
        return Trip.builder()
                .city(city)
                .title(city + " 여행")
                .arrivalDate(arrivalDate)
                .departureDate(departureDate)
                .build();
    }
}

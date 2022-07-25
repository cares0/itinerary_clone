package com.triple.itinerary.web.trip.request;

import com.triple.itinerary.domain.trip.entity.Partner;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.TripStyle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@NoArgsConstructor(access = PRIVATE)
public class TripEditRequest {

    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Partner partner;
    private TripStyle tripStyle;

    public Trip toEntity() {
        return Trip.builder()
                .city(this.city)
                .title(this.title)
                .arrivalDate(this.arrivalDate)
                .departureDate(this.departureDate)
                .partner(this.partner)
                .tripStyle(this.tripStyle)
                .build();
    }
}
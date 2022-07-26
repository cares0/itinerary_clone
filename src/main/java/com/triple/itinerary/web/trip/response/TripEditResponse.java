package com.triple.itinerary.web.trip.response;

import com.triple.itinerary.domain.trip.entity.Partner;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.TripStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class TripEditResponse {

    private Long id;
    private String city;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Partner partner;
    private TripStyle tripStyle;

    public static TripEditResponse toResponse(Trip trip) {
        return TripEditResponse.builder()
                .id(trip.getId())
                .city(trip.getCity())
                .title(trip.getTitle())
                .arrivalDate(trip.getPeriod().getArrivalDate())
                .departureDate(trip.getPeriod().getDepartureDate())
                .partner(trip.getPartner())
                .tripStyle(trip.getTripStyle())
                .build();
    }
}
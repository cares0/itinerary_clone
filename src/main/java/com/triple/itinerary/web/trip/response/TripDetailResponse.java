package com.triple.itinerary.web.trip.response;

import com.triple.itinerary.domain.trip.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
public class TripDetailResponse {

    private Long id;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private String partner;
    private String tripStyle;

    public static TripDetailResponse toResponse(Trip trip) {
        String partner = Objects.isNull(trip.getPartner()) ?
                null : trip.getPartner().getKorName();

        String tripStyle = Objects.isNull(trip.getTripStyle()) ?
                null : trip.getTripStyle().getKorName();

        return TripDetailResponse.builder()
                .id(trip.getId())
                .title(trip.getTitle())
                .arrivalDate(trip.getPeriod().getArrivalDate())
                .departureDate(trip.getPeriod().getDepartureDate())
                .partner(partner)
                .tripStyle(tripStyle)
                .build();
    }
}

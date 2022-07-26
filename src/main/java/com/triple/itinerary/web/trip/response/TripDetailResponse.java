package com.triple.itinerary.web.trip.response;

import com.triple.itinerary.domain.trip.entity.Partner;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.TripStyle;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class TripDetailResponse {

    private Long id;
    private String title;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private Partner partner;
    private TripStyle tripStyle;

    public static TripDetailResponse toResponse(Trip trip) {
        return TripDetailResponse.builder()
                .id(trip.getId())
                .title(trip.getTitle())
                .arrivalDate(trip.getPeriod().getArrivalDate())
                .departureDate(trip.getPeriod().getDepartureDate())
                .partner(trip.getPartner())
                .tripStyle(trip.getTripStyle())
                .build();
    }
}

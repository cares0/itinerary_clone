package com.triple.itinerary.web.trip.response;

import lombok.*;

import java.time.LocalDate;

import static lombok.AccessLevel.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class TripListResponse {

    private Long id;
    private String title;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
}

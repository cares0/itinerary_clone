package com.triple.itinerary.domain.trip.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Trip {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "trip_id")
    private Long id;

    private String city;

    private String title;

    private Period period;

    private Partner partner;

    private TripStyle tripStyle;

    @Builder
    public Trip(String city, LocalDate arrivalDate, LocalDate departureDate) {
        this.city = city;
        this.title = title;
        this.period = Period.builder().arrivalDate(arrivalDate).departureDate(departureDate).build();
    }


}

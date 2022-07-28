package com.triple.itinerary.domain.trip.entity;

import com.triple.itinerary.domain.common.BaseEntity;
import com.triple.itinerary.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Trip extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "trip_id")
    private Long id;

/*    @OneToMany(fetch = LAZY, mappedBy = "trip")
    private List<UserTrip> userTrips = new ArrayList<>();*/

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String title;

    private Period period;

    @Enumerated(value = STRING)
    private Partner partner;

    @Enumerated(value = STRING)
    private TripStyle tripStyle;

    @Builder
    public Trip(String city, String title, LocalDate arrivalDate, LocalDate departureDate, Partner partner, TripStyle tripStyle) {
        this.city = city;
        this.title = title;
        this.period = Period.builder().arrivalDate(arrivalDate).departureDate(departureDate).build();
        this.partner = partner;
        this.tripStyle = tripStyle;
    }

    public void update(Trip modified) {
        String city = modified.getCity();
        if (Objects.nonNull(city)) {
            this.city = city;
        }
        String title = modified.getTitle();
        if (Objects.nonNull(title)) {
            this.title = title;
        }

        LocalDate arrivalDate = modified.getPeriod().getArrivalDate();
        LocalDate departureDate = modified.getPeriod().getDepartureDate();
        if (Objects.nonNull(arrivalDate) && Objects.nonNull(departureDate)) {
            this.period.updateDate(arrivalDate, departureDate);
        }

        Partner partner = modified.getPartner();
        if (Objects.nonNull(partner)) {
            this.partner = partner;
        }
        TripStyle tripStyle = modified.getTripStyle();
        if (Objects.nonNull(tripStyle)) {
            this.tripStyle = tripStyle;
        }
    }

}

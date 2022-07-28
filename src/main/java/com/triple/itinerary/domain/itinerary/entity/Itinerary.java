package com.triple.itinerary.domain.itinerary.entity;

import com.triple.itinerary.domain.trip.entity.Trip;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalTime;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Itinerary {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "itinerary_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(value = STRING)
    private ItineraryType itineraryType;

    private Long typeId;

    private Integer visitDay; // 몇일 차에 방문할 것인지

    private Integer visitOrder;

    private Integer arrangeOrder;

    private String title;

    private String subTitle;

    private LocalTime visitTime;

    private String memo;

    @Builder
    private Itinerary(ItineraryType itineraryType, Long typeId, Integer visitDay) {
        this.itineraryType = itineraryType;
        this.typeId = typeId;
        this.visitDay = visitDay;
    }

    public void initItinerary(Trip trip, String title, String subTitle, Integer arrangeOrder, Integer visitOrder) {
        this.trip = trip;
        this.title = title;
        this.subTitle = subTitle;
        this.arrangeOrder = arrangeOrder;
        this.visitOrder = visitOrder;
    }

}
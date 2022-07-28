package com.triple.itinerary.domain.place.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Place {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Enumerated(value = STRING)
    private PlaceType placeType;

    private Double rating;

    @Builder
    private Place(String name, String location, PlaceType placeType) {
        this.name = name;
        this.location = location;
        this.placeType = placeType;
    }
}

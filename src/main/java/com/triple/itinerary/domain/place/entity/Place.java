package com.triple.itinerary.domain.place.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Place {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    private String name;

    private String location;

    @Enumerated(value = STRING)
    private PlaceType placeType;

    private Double rating;

}

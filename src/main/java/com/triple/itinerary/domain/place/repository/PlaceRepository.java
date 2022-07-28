package com.triple.itinerary.domain.place.repository;

import com.triple.itinerary.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
package com.triple.itinerary.domain.trip.repository;

import com.triple.itinerary.domain.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {

}

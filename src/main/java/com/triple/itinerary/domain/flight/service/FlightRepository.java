package com.triple.itinerary.domain.flight.service;

import com.triple.itinerary.domain.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}

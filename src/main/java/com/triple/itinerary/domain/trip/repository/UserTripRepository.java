package com.triple.itinerary.domain.trip.repository;

import com.triple.itinerary.domain.trip.entity.UserTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTripRepository extends JpaRepository<UserTrip, Long> {
}

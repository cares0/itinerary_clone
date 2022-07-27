package com.triple.itinerary.domain.itinerary.repository;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    @Query("select i from Itinerary i where i.trip.id = :tripId and i.day = :day")
    List<Itinerary> findAllByTripIdAndDay(@Param("tripId") Long tripId, @Param("day") Integer day);
}

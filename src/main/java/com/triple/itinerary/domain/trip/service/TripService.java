package com.triple.itinerary.domain.trip.service;

import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public Long add(Trip trip) {
        return tripRepository.save(trip).getId();
    }


}

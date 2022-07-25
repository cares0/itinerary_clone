package com.triple.itinerary.domain.trip.service;

import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripQueryService {

    private TripRepository tripRepository;

    public Trip getOne(Long tripId) {
        return findTrip(tripId);
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }
}

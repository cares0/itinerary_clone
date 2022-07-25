package com.triple.itinerary.web.trip.query;

import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.web.trip.response.TripDetailResponse;
import com.triple.itinerary.web.trip.response.TripListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripQueryService {

    private TripQueryRepository tripQueryRepository;

    public TripDetailResponse getOne(Long tripId) {
        return findTrip(tripId);
    }

    public List<TripListResponse> getList(Long userId) {
        return tripQueryRepository.findAllByUserId(userId);
    }

    private TripDetailResponse findTrip(Long tripId) {
        return tripQueryRepository.findByTripId(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }
}

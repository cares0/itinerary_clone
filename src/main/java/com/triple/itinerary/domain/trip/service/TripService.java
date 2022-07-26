package com.triple.itinerary.domain.trip.service;

import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.entity.UserTrip;
import com.triple.itinerary.domain.trip.repository.TripRepository;
import com.triple.itinerary.domain.trip.repository.UserTripRepository;
import com.triple.itinerary.domain.user.User;
import com.triple.itinerary.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final UserTripRepository userTripRepository;
    private final UserRepository userRepository;

    public Long add(Long userId, Trip trip) {
        User user = findUser(userId);
        tripRepository.save(trip);

        UserTrip userTrip = createUserTrip(user, trip);
        userTripRepository.save(userTrip);
        return trip.getId();
    }

    public Trip modify(Long tripId, Trip modified) {
        Trip original = findTrip(tripId);
        original.update(modified);
        return original;
    }

    public Long invite(Long userId, Long tripId) {
        User user = findUser(userId);
        Trip trip = findTrip(tripId);

        UserTrip userTrip = createUserTrip(user, trip);
        return userTripRepository.save(userTrip).getId();
    }

    private UserTrip createUserTrip(User user, Trip trip) {
        return UserTrip.builder()
                .user(user)
                .trip(trip)
                .build();
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 유저를 찾을 수 없음"));
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }
}

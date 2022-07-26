package com.triple.itinerary.web.trip.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.itinerary.domain.trip.entity.QUserTrip;
import com.triple.itinerary.web.trip.response.TripDetailResponse;
import com.triple.itinerary.web.trip.response.TripListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.triple.itinerary.domain.trip.entity.QTrip.*;
import static com.triple.itinerary.domain.trip.entity.QUserTrip.*;

@Repository
@RequiredArgsConstructor
public class TripQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<TripDetailResponse> findByTripId(Long tripId) {
        return Optional.ofNullable(
                jpaQueryFactory.select(Projections.bean(TripDetailResponse.class,
                                trip.id,
                                trip.title,
                                trip.period.arrivalDate,
                                trip.period.departureDate,
                                trip.tripStyle,
                                trip.partner))
                        .from(trip)
                        .where(trip.id.eq(tripId))
                        .fetchOne());
    }

    public List<TripListResponse> findAllByUserId(Long userId) {
        return jpaQueryFactory.select(Projections.bean(TripListResponse.class,
                        trip.id,
                        trip.title,
                        trip.period.departureDate,
                        trip.period.arrivalDate))
                .from(trip)
                .join(userTrip).on(userTrip.trip.eq(trip))
                .where(userTrip.user.id.eq(userId))
                .fetch();
    }
}

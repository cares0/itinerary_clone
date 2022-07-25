package com.triple.itinerary.web.trip.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.itinerary.web.trip.response.TripDetailResponse;
import com.triple.itinerary.web.trip.response.TripListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.triple.itinerary.domain.trip.entity.QTrip.*;

@Repository
@RequiredArgsConstructor
public class TripQueryRepository {

    private JPAQueryFactory jpaQueryFactory;

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
                .where(trip.user.id.eq(userId))
                .fetch();
    }
}

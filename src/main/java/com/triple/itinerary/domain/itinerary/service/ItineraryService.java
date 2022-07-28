package com.triple.itinerary.domain.itinerary.service;

import com.triple.itinerary.domain.exception.ConcreteNotFoundException;
import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.repository.ItineraryRepository;
import com.triple.itinerary.domain.itinerary.service.ordercalculator.OrderCalculator;
import com.triple.itinerary.domain.itinerary.service.titlemaker.TitleMaker;
import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItineraryService {

    private final List<OrderCalculator> orderCalculators;
    private final List<TitleMaker> titleMakers;
    private final ItineraryRepository itineraryRepository;
    private final TripRepository tripRepository;

    public Long add(Long tripId, Itinerary itinerary) {

        TitleMaker titleMaker = getTitleMaker(itinerary);
        OrderCalculator orderCalculator = getOrderCalculator(itinerary);

        String title = titleMaker.makeTitle(itinerary);
        String subTitle = titleMaker.makeSubTitle(itinerary);

        List<Itinerary> findItineraries = itineraryRepository
                .findAllByTripIdAndDay(tripId, itinerary.getVisitDay());

        System.out.println("findItineraries.isEmpty() = " + findItineraries.isEmpty());

        Integer arrangeOrder = orderCalculator.createInitialArrangeOrder(findItineraries);
        Integer visitOrder = orderCalculator.createInitialVisitOrder(findItineraries);

        Trip trip = findTrip(tripId);
        itinerary.initItinerary(trip, title, subTitle, arrangeOrder, visitOrder);

        return itineraryRepository.save(itinerary).getId();
    }

    private OrderCalculator getOrderCalculator(Itinerary itinerary) {
        return orderCalculators.stream()
                .filter(tm -> tm.isSupported(itinerary))
                .findAny()
                .orElseThrow(()
                        -> new ConcreteNotFoundException("지원되는 OrderCalculator 구현 클래스를 찾을 수 없습니다."));
    }

    private TitleMaker getTitleMaker(Itinerary itinerary) {
        return titleMakers.stream()
                .filter(tm -> tm.isSupported(itinerary))
                .findAny()
                .orElseThrow(()
                        -> new ConcreteNotFoundException("지원되는 TitleMaker 구현 클래스를 찾을 수 없습니다."));
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 여행을 찾을 수 없음"));
    }

}

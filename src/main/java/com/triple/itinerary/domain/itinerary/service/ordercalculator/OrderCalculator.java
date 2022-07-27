package com.triple.itinerary.domain.itinerary.service.ordercalculator;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;

import java.util.List;

public interface OrderCalculator {

    boolean isSupported(Itinerary itinerary);

    Integer createInitialArrangeOrder(List<Itinerary> findItineraries);

    Integer createInitialVisitOrder(List<Itinerary> findItineraries);

}

package com.triple.itinerary.domain.itinerary.service.ordercalculator;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class PlaceOrderCalculator implements OrderCalculator {

    @Override
    public boolean isSupported(Itinerary itinerary) {
        return itinerary.getItineraryType() == ItineraryType.PLACE;
    }

    @Override
    public Integer createInitialArrangeOrder(List<Itinerary> findItineraries) {
        return findItineraries.stream()
                .map(Itinerary::getArrangeOrder)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findAny().orElse(1);
    }

    @Override
    public Integer createInitialVisitOrder(List<Itinerary> findItineraries) {
        return findItineraries.stream()
                .map(Itinerary::getVisitOrder)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findAny().orElse(1);
    }
}

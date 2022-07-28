package com.triple.itinerary.domain.itinerary.service.ordercalculator;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class FlightOrderCalculator implements OrderCalculator {

    @Override
    public boolean isSupported(Itinerary itinerary) {
        return itinerary.getItineraryType() == ItineraryType.FLIGHT;
    }

    @Override
    public Integer createInitialArrangeOrder(List<Itinerary> findItineraries) {
        Integer lastArrangeOrder = findItineraries.stream()
                .map(Itinerary::getArrangeOrder)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findAny().orElse(0);
        return lastArrangeOrder + 1;
    }

    @Override
    public Integer createInitialVisitOrder(List<Itinerary> findItineraries) {
        return 0;
    }
}

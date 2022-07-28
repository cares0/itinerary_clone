package com.triple.itinerary.domain.itinerary.service.ordercalculator;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class PlaceOrderCalculator implements OrderCalculator {

    @Override
    public boolean isSupported(Itinerary itinerary) {
        return itinerary.getItineraryType() == ItineraryType.PLACE;
    }

    @Override
    public Integer createInitialArrangeOrder(List<Itinerary> findItineraries) {
        Integer lastArrangeOrder = findItineraries.stream()
                .map(Itinerary::getArrangeOrder)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findAny().orElse(0);
        for (Itinerary findItinerary : findItineraries) {
            log.info("itinerary: ", findItinerary);
        }
        log.info("lastArrangeOrder {}", lastArrangeOrder);
        return lastArrangeOrder + 1;
    }

    @Override
    public Integer createInitialVisitOrder(List<Itinerary> findItineraries) {
        Integer lastVisitOrder = findItineraries.stream()
                .map(Itinerary::getVisitOrder)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findAny().orElse(0);
        return lastVisitOrder + 1;
    }
}

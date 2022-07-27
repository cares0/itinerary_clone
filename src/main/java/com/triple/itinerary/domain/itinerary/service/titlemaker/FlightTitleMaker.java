package com.triple.itinerary.domain.itinerary.service.titlemaker;

import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.flight.entity.Flight;
import com.triple.itinerary.domain.flight.service.FlightRepository;
import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import com.triple.itinerary.domain.itinerary.service.titlemaker.TitleMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightTitleMaker implements TitleMaker {

    private final FlightRepository flightRepository;

    @Override
    public boolean isSupported(Itinerary itinerary) {
        return itinerary.getItineraryType() == ItineraryType.FLIGHT;
    }

    @Override
    public String makeTitle(Itinerary itinerary) {
        Flight flight = findFlight(itinerary);

        return flight.getDepartureAirport() + flight.getDepartureTime()
                + " - " + flight.getArrivalAirport() + flight.getDepartureAirport();
    }

    @Override
    public String makeSubTitle(Itinerary itinerary) {
        Flight flight = findFlight(itinerary);

        return flight.getAirline() + " · " + flight.getFlightNumber();
    }

    private Flight findFlight(Itinerary itinerary) {
        return flightRepository.findById(itinerary.getTypeId()).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 항공 정보를 찾을 수 없음"));
    }
}

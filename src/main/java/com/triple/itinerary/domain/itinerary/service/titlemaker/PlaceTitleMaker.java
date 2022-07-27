package com.triple.itinerary.domain.itinerary.service.titlemaker;

import com.triple.itinerary.domain.exception.EntityNotFoundException;
import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import com.triple.itinerary.domain.itinerary.service.titlemaker.TitleMaker;
import com.triple.itinerary.domain.place.entity.Place;
import com.triple.itinerary.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaceTitleMaker implements TitleMaker {

    private final PlaceRepository placeRepository;

    @Override
    public boolean isSupported(Itinerary itinerary) {
        return itinerary.getItineraryType() == ItineraryType.PLACE;
    }

    @Override
    public String makeTitle(Itinerary itinerary) {
        Place place = findPlace(itinerary);

        return place.getName();
    }

    @Override
    public String makeSubTitle(Itinerary itinerary) {
        Place place = findPlace(itinerary);

        return place.getPlaceType().getKorName() + " · " + place.getLocation();
    }

    private Place findPlace(Itinerary itinerary) {
        return placeRepository.findById(itinerary.getTypeId()).orElseThrow(() ->
                new EntityNotFoundException("해당 ID와 일치하는 장소 정보를 찾을 수 없음"));
    }
}

package com.triple.itinerary.web.itinerary.request;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.entity.ItineraryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class ItinerarySaveRequest {

    @NotNull
    private Long tripId;

    @NotNull
    private ItineraryType itineraryType;

    @NotNull
    private Long typeId;

    @NotNull
    private Integer visitDay;

    public Itinerary toEntity() {
        return Itinerary.builder()
                .itineraryType(itineraryType)
                .typeId(typeId)
                .visitDay(visitDay)
                .build();
    }
}

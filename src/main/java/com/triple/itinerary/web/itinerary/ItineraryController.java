package com.triple.itinerary.web.itinerary;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;
import com.triple.itinerary.domain.itinerary.service.ItineraryService;
import com.triple.itinerary.web.common.Result;
import com.triple.itinerary.web.itinerary.request.ItinerarySaveRequest;
import com.triple.itinerary.web.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itineraries")
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping
    public Result<Long> itineraryAdd(@RequestBody ItinerarySaveRequest itinerarySaveRequest,
                                     BindingResult bindingResult) {
        ValidationUtils.validate(bindingResult);
        Itinerary itinerary = itinerarySaveRequest.toEntity();
        Long savedId = itineraryService.add(itinerarySaveRequest.getTripId(), itinerary);
        return new Result<>(savedId);
    }
}

package com.triple.itinerary.web.trip;

import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.domain.trip.service.TripQueryService;
import com.triple.itinerary.domain.trip.service.TripService;
import com.triple.itinerary.web.common.Result;
import com.triple.itinerary.web.trip.request.TripEditRequest;
import com.triple.itinerary.web.trip.request.TripSaveRequest;
import com.triple.itinerary.web.trip.response.TripEditResponse;
import com.triple.itinerary.web.trip.response.TripSearchResponse;
import com.triple.itinerary.web.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private TripService tripService;
    private TripQueryService tripQueryService;

    @GetMapping("/{tripId}")
    public TripSearchResponse tripDetail(@PathVariable Long tripId) {
        Trip trip = tripQueryService.getOne(tripId);
        return TripSearchResponse.toResponse(trip);
    }

    @PostMapping
    public Result<Long> tripAdd(@RequestBody TripSaveRequest tripSaveRequest,
                                BindingResult bindingResult) {
        ValidationUtils.validate(bindingResult);

        Trip trip = tripSaveRequest.toEntity();
        Long savedId = tripService.add(tripSaveRequest.getUserId(), trip);
        return new Result<>(savedId);
    }

    @PatchMapping("/{tripId}")
    public TripEditResponse tripModify(@PathVariable Long tripId,
                                       @RequestBody TripEditRequest tripEditRequest) {
        Trip modified = tripEditRequest.toEntity();
        modified = tripService.modify(tripId, modified);
        return TripEditResponse.toResponse(modified);
    }



}

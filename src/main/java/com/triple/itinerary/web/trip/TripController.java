package com.triple.itinerary.web.trip;

import com.triple.itinerary.domain.trip.entity.Trip;
import com.triple.itinerary.web.common.ListResult;
import com.triple.itinerary.web.trip.query.TripQueryService;
import com.triple.itinerary.domain.trip.service.TripService;
import com.triple.itinerary.web.common.Result;
import com.triple.itinerary.web.trip.request.TripEditRequest;
import com.triple.itinerary.web.trip.request.TripSaveRequest;
import com.triple.itinerary.web.trip.response.TripEditResponse;
import com.triple.itinerary.web.trip.response.TripDetailResponse;
import com.triple.itinerary.web.trip.response.TripListResponse;
import com.triple.itinerary.web.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final TripQueryService tripQueryService;

    @GetMapping("/{tripId}")
    public TripDetailResponse tripDetail(@PathVariable Long tripId) {
        return tripQueryService.getOne(tripId);
    }

    @GetMapping
    public ListResult<TripListResponse> tripList(@RequestParam Long userId) {
        List<TripListResponse> contents = tripQueryService.getList(userId);
        return new ListResult<>(contents.size(), contents);
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

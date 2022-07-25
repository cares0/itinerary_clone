package com.triple.itinerary.web.common;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListResult<T> {

    private Integer count;
    private List<T> contents;

}

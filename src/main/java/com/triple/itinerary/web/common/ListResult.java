package com.triple.itinerary.web.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class ListResult<T> {

    private Integer count;
    private List<T> contents;

}

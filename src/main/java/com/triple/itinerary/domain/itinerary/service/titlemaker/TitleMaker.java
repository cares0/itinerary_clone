package com.triple.itinerary.domain.itinerary.service.titlemaker;

import com.triple.itinerary.domain.itinerary.entity.Itinerary;

public interface TitleMaker {

    // TODO 나중에 리팩터링 할 것(OrderCalculator에서도 사용됨, 클래스 상속이나 다중 구현으로 해결해볼 것)
    boolean isSupported(Itinerary itinerary);

    String makeTitle(Itinerary itinerary);

    String makeSubTitle(Itinerary itinerary);
}

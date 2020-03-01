package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.PlacedOrderItem;

import java.util.List;

public interface PlacedOrderItemService {
    List<PlacedOrderItem> findAll();

    PlacedOrderItem save(PlacedOrderItem placedOrderItem);

    PlacedOrderItem findByID(Integer ID);
}

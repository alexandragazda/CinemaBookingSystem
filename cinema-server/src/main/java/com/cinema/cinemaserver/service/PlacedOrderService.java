package com.cinema.cinemaserver.service;

import com.cinema.cinemaserver.domain.PlacedOrder;
import com.cinema.cinemaserver.domain.dtos.OrderDTO;
import com.cinema.cinemaserver.domain.dtos.OrderInfoDTO;

import java.util.List;

public interface PlacedOrderService {
    List<PlacedOrder> findAll();

    PlacedOrder save(PlacedOrder placedOrder);

    PlacedOrder save(OrderDTO orderDTO);

    PlacedOrder findByID(Integer ID);

    List<PlacedOrder> findAllByUserEmail(String userEmail);

    List<OrderInfoDTO> findFirstExpiredOrders(String userEmail);
}

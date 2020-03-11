package com.cinema.cinemaserver.service.implementation;

import com.cinema.cinemaserver.domain.PlacedOrderItem;
import com.cinema.cinemaserver.domain.validator.Validator;
import com.cinema.cinemaserver.repository.PlacedOrderItemRepository;
import com.cinema.cinemaserver.service.PlacedOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacedOrderItemServiceImplementation implements PlacedOrderItemService {
    @Autowired
    private PlacedOrderItemRepository placedOrderItemRepository;

    @Autowired
    private Validator<PlacedOrderItem> validator;

    @Override
    public List<PlacedOrderItem> findAll() {
        return placedOrderItemRepository.findAll();
    }

    @Override
    public PlacedOrderItem save(PlacedOrderItem placedOrderItem) {
        validator.validate(placedOrderItem);

        placedOrderItemRepository.save(placedOrderItem);

        return placedOrderItem;
    }

    @Override
    public PlacedOrderItem findByID(Integer ID) {
        if(placedOrderItemRepository.findById(ID).isPresent()) return placedOrderItemRepository.findById(ID).get();
        return null;
    }

    @Override
    public List<PlacedOrderItem> findAllByPlacedOrderID(Integer placedOrderID) {
        return placedOrderItemRepository.findAllByPlacedOrderID(placedOrderID);
    }
}

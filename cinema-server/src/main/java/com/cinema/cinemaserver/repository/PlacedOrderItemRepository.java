package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.PlacedOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedOrderItemRepository extends JpaRepository<PlacedOrderItem, Integer> { }

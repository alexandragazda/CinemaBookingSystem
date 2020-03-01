package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, Integer> { }

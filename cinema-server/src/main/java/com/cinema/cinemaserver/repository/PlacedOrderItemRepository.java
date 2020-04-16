package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.PlacedOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacedOrderItemRepository extends JpaRepository<PlacedOrderItem, Integer> {

    //returns all the items corresponding to a specified order
    @Query("select i from PlacedOrderItem i inner join PlacedOrder o on o.ID = i.placedOrder.ID where o.ID = ?1")
    List<PlacedOrderItem> findAllByPlacedOrderID(Integer placedOrderID);
}

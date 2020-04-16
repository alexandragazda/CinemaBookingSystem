package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, Integer> {

    //returns all the orders made by a specified user
    @Query("select po from PlacedOrder po inner join User u on u.email = po.user.email where u.email = ?1")
    List<PlacedOrder> findAllByUserEmail(String userEmail);
}

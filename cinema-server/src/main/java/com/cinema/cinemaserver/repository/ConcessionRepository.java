package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Concession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcessionRepository extends JpaRepository<Concession, Integer> {
//    //returns all the concessions with a specific concession type
//    @Query("select c from Concession c inner join ConcessionType ct on ct.type = c.concessionType.type where ct.type=?1")
//    List<Concession> findAllByConcessionType(String concessionType);
}

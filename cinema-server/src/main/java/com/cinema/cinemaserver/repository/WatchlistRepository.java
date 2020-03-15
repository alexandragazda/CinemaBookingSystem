package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,String> {
}

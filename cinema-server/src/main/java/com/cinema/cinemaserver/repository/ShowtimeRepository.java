package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Integer> {

    @Query("select s from Showtime s where s.movie.ID=?1")
    List<Showtime> findAllByMovieId(Integer movieId);

}
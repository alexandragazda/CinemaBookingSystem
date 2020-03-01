package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.ConcessionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcessionTypeRepository extends JpaRepository<ConcessionType, String> { }

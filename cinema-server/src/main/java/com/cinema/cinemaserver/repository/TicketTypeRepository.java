package com.cinema.cinemaserver.repository;

import com.cinema.cinemaserver.domain.TicketType;
import com.cinema.cinemaserver.domain.enums.TicketTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, TicketTypeEnum> { }
package com.cinema.cinemaserver;

import com.cinema.cinemaserver.domain.Role;
//import com.cinema.cinemaserver.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaServerApplication {

	public static void main(String[] args) {
//		RoleRepo roleRepo=new RoleRepo();
//		roleRepo.getRoles();

		SpringApplication.run(CinemaServerApplication.class, args);
	}

}

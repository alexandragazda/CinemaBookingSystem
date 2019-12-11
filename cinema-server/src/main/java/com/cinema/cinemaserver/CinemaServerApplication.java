package com.cinema.cinemaserver;

import com.cinema.cinemaserver.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CinemaServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CinemaServerApplication.class, args);

	}

}

//@SpringBootApplication
//public class CinemaServerApplication extends SpringBootServletInitializer {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(CinemaServerApplication.class);
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(CinemaServerApplication.class, args);
//	}
//
//}

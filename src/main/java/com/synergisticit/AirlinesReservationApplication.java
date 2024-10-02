package com.synergisticit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class AirlinesReservationApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AirlinesReservationApplication.class, args);
//	}
//
//}

public class AirlinesReservationApplication extends SpringBootServletInitializer {
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AirlinesReservationApplication.class);
	}

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/airlineApp");
		SpringApplication.run(AirlinesReservationApplication.class, args);
	}

}

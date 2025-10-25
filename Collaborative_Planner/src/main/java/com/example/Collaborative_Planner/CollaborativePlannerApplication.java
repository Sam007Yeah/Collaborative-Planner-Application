package com.example.Collaborative_Planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CollaborativePlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollaborativePlannerApplication.class, args);
	}

}

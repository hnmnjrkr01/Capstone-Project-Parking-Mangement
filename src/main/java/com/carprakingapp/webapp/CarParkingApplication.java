package com.carprakingapp.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.carprakingapp.webapp")
public class CarParkingApplication {

	public static void main(String[] args) {

		SpringApplication.run(CarParkingApplication.class, args);

		System.out.println("====Welcome To Car Parking Application====");
	}

}

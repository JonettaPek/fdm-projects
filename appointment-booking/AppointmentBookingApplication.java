package com.fdmgroup.appointmentbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.Persistence;

@SpringBootApplication
public class AppointmentBookingApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AppointmentBookingApplication.class, args);
	}

}

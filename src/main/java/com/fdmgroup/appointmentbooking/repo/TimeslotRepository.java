package com.fdmgroup.appointmentbooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.appointmentbooking.model.Timeslot;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Long>{

	public Optional<Timeslot> findByStartTime(String startTime);

	
}
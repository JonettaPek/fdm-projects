package com.fdmgroup.appointmentbooking.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.appointmentbooking.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	public List<Doctor> findByFirstName(String firstName);
	
	public List<Doctor> findByLastName(String lastName);
	
	public Optional<Doctor> findByEmail(String email);
	
	public List<Doctor> findBySpecialty(String specialty);
	

	
	
}

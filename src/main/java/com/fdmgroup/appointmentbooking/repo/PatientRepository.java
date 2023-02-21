package com.fdmgroup.appointmentbooking.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.appointmentbooking.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

	public List<Patient> findByFirstName(String firstName);
	
	public List<Patient> findByLastName(String lastName);
	
	public Optional<Patient> findByEmail(String email);

}

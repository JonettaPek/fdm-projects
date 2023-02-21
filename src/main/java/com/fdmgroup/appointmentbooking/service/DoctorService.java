package com.fdmgroup.appointmentbooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.appointmentbooking.model.Doctor;
import com.fdmgroup.appointmentbooking.repo.DoctorRepository;

@Service
public class DoctorService {

	DoctorRepository doctorRepository;
	
	@Autowired
	public DoctorService(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}

	public boolean emailExists(String email) {
		return doctorRepository.findByEmail(email).isPresent();
	}
	
	public boolean emailDoesNotExist(String email) {
		return doctorRepository.findByEmail(email).isEmpty();
	}
	
	public boolean bothPasswordsMatch(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}
	
	public boolean correctPassword(String email, String password) {
		Optional<Doctor> retrievedDoctor = doctorRepository.findByEmail(email);
		if (retrievedDoctor.isPresent()) {
			return retrievedDoctor.get().getPassword().equals(password);
		}
		return false;
	}
	
	public boolean registrationSuccessful(Doctor doctor, String email, String password, String confirmPassword) {
		if (emailDoesNotExist(email) && bothPasswordsMatch(password, confirmPassword)) {
			doctorRepository.save(doctor);
			return true;
		}
		return false;
	}
	
	public boolean loginSuccessful(String email, String password) {
		if (emailExists(email) && correctPassword(email, password)) {
			return true;
		}
		return false;
	}
	
	public Doctor retrieveDoctorByEmail(String email) {
		Optional<Doctor> retrievedDoctor = doctorRepository.findByEmail(email);
		if(retrievedDoctor.isPresent()) {
			return retrievedDoctor.get();
		}
		
		return new Doctor();
	}
	
}

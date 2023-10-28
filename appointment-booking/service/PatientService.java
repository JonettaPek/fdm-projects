package com.fdmgroup.appointmentbooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.appointmentbooking.exception.PatientNotFoundException;
import com.fdmgroup.appointmentbooking.model.Patient;
import com.fdmgroup.appointmentbooking.repo.PatientRepository;

@Service
public class PatientService {

	private final PatientRepository patientRepository;
	
	@Autowired
	public PatientService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	
	
	
	
	public boolean emailExists(String email) {
		return patientRepository.findByEmail(email).isPresent();
	}
	
	public boolean emailDoesNotExist(String email) {
		return patientRepository.findByEmail(email).isEmpty();
	}
	
	public boolean bothPasswordsMatch(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}
	
	public boolean correctPassword(String email, String password) {
		Optional<Patient> retrievedPatient = patientRepository.findByEmail(email);
		if (retrievedPatient.isPresent()) {
			return retrievedPatient.get().getPassword().equals(password);
		}
		return false;
	}
	
	
	
	
	
	public boolean registrationSuccessful(Patient patient, String email, String password, String confirmPassword) {
		if (emailDoesNotExist(email) && bothPasswordsMatch(password, confirmPassword)) {
			patientRepository.save(patient);
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
	
	
	
	
	public Patient retrievePatientById(long patientID) {
		Optional<Patient> retrievedPatient = patientRepository.findById(patientID);
		if (retrievedPatient.isPresent()) {
			return retrievedPatient.get();
		}
		return retrievedPatient.orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientID + " does not exist"));
	}
	
	public Patient retrievePatientByEmail(String email) {
		Optional<Patient> retrievedPatient = patientRepository.findByEmail(email);
		if (retrievedPatient.isPresent()) {
			return retrievedPatient.get();
		}
		return retrievedPatient.orElseThrow(() -> new PatientNotFoundException("Patient with email " + email + " does not exist"));
	}
	
	public long retrieveIdByEmail(String email) {
		Optional<Patient> retrievedPatient = patientRepository.findByEmail(email);
		if (retrievedPatient.isPresent()) {
			return retrievedPatient.get().getPatientID();
		}
		return 0L;
	}
	
	
	
	
	
	
}

package com.fdmgroup.appointmentbooking.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
@Component
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private long patientID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "date_of_birth")
	private String dateOfBirth;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments = new ArrayList<Appointment>();
	
	public Patient() {
		super();
	}
	
	public Patient(long patientID, String firstName, String lastName, String address, String dateOfBirth, String email, String password) {
		super();
		this.patientID = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
	}

	public long getPatientID() {
		return this.patientID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
	
	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setPatientID(long patientID) {
		this.patientID = patientID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}
}

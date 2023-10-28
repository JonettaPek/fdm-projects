package com.fdmgroup.appointmentbooking.model;


import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
@Component
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private long appointmentID;
	
	@Column(name = "date_of_visit")
	private String dateOfVisit;
	
	@Column(name = "time_of_visit")
	private String timeOfVisit;
	
	@Column(name = "location")
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "fk_patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "fk_doctor_id")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "fk_timeslot_id")
	private Timeslot timeslot;
	
	public Appointment() {
		super();
	}
	
	public Appointment(long appointmentID, String dateOfVisit, String timeOfVisit, String location, Patient patient,
			Doctor doctor, Timeslot timeslot) {
		super();
		this.appointmentID = appointmentID;
		this.dateOfVisit = dateOfVisit;
		this.timeOfVisit = timeOfVisit;
		this.location = location;
		this.patient = patient;
		this.doctor = doctor;
		this.timeslot = timeslot;
	}

	public long getAppointmentID() {
		return this.appointmentID;
	}

	public String getDateOfVisit() {
		return this.dateOfVisit;
	}

	public String getTimeOfVisit() {
		return this.timeOfVisit;
	}

	public String getLocation() {
		return this.location;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public Timeslot getTimeslot() {
		return this.timeslot;
	}

	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public void setTimeOfVisit(String timeOfVisit) {
		this.timeOfVisit = timeOfVisit;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}



	

	
	
}



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
@Table(name = "timeslot")
@Component
public class Timeslot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeslot_id")
	private long timeslotID;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "duration")
	private String duration;
	
	@OneToMany(mappedBy = "timeslot")
	private List<Appointment> appointmentsPerTimeslot = new ArrayList<Appointment>();
	
	public Timeslot() {
		super();
	}

	public Timeslot(long timeslotID, String startTime, String endTime, String duration) {
		super();
		this.timeslotID = timeslotID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
	}

	public long getTimeslotID() {
		return this.timeslotID;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public String getDuration() {
		return this.duration;
	}

	public List<Appointment> getAppointmentsPerTimeslot() {
		return this.appointmentsPerTimeslot;
	}

	public void setTimeslotID(long timeslotID) {
		this.timeslotID = timeslotID;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setAppointmentsPerTimeslot(List<Appointment> appointmentsPerTimeslot) {
		this.appointmentsPerTimeslot = appointmentsPerTimeslot;
	}
	
	public void addAppointment(Appointment appointment) {
		appointmentsPerTimeslot.add(appointment);
	}
	
	
}

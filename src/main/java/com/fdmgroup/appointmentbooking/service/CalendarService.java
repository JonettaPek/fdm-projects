package com.fdmgroup.appointmentbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.appointmentbooking.model.Appointment;
import com.fdmgroup.appointmentbooking.repo.AppointmentRepository;
import com.fdmgroup.appointmentbooking.repo.TimeslotRepository;

@Service
public class CalendarService {

	private final AppointmentRepository appointmentRepository;
	private final TimeslotRepository timeslotRepository;
	
	@Autowired
	public CalendarService(AppointmentRepository appointmentRepository, TimeslotRepository timeslotRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.timeslotRepository = timeslotRepository;
	}
	
	public boolean appointmentExists(Appointment appointment) {
		if (appointmentRepository.findByPatientIdAndDateOfVisitAndTimeOfVisit(appointment.getPatient().getPatientID(), 
				appointment.getDateOfVisit(), 
				appointment.getTimeOfVisit()).isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean newBooking(Appointment appointment) {
		if (appointmentRepository.findByPatientIdAndDateOfVisitAndTimeOfVisit(appointment.getPatient().getPatientID(), 
				appointment.getDateOfVisit(), 
				appointment.getTimeOfVisit()).isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean bookSuccessfully(Appointment appointment) {
		if (newBooking(appointment)) {
			appointmentRepository.save(appointment);
			return true;
		}
		return false;
	}
	
	public boolean cancelSuccessfully(long appointmentID) {
		Appointment appointment = appointmentRepository.findById(appointmentID);
		if (appointmentExists) {
			
		}
	}
	
}

package com.fdmgroup.appointmentbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.appointmentbooking.model.Appointment;
import com.fdmgroup.appointmentbooking.model.Patient;
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
	
	
	
	
	public Appointment retrieveAppointmentByAppointmentId(long appointmentID) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentID);
		if (appointment.isPresent()) {
			return appointment.get();
		}
		return new Appointment();
	}
	
	
	
	
	public List<Appointment> retrieveAppointmentsByPatientId(long patientID) {
		return appointmentRepository.findAllByPatientId(patientID);
	}
	
	public List<Appointment> retrieveAppointmentsByDoctorId(long doctorID) {
		return appointmentRepository.findAllByDoctorId(doctorID);
	}
	
	public List<Appointment> retrieveAppointmentsByTimeslotId(long timeslotID) {
		return appointmentRepository.findAllByTimeslotId(timeslotID);
	}
	
	
	
	
	public boolean appointmentExists(long appointmentID) {
		return appointmentRepository.existsById(appointmentID);
	}
	
	public boolean bookedBefore(long patientID, String dateOfVisit, String timeOfVisit) {
		if (appointmentRepository.findByPatientIdAndDateOfVisitAndTimeOfVisit(patientID, 
			dateOfVisit, 
			timeOfVisit).isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean newBooking(long patientID, String dateOfVisit, String timeOfVisit) {
		if (appointmentRepository.findByPatientIdAndDateOfVisitAndTimeOfVisit(patientID, 
			dateOfVisit, 
			timeOfVisit).isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean bookedByPatient(long appointmentID, long patientID) {
		if (appointmentRepository.findByAppointmentIdAndPatientId(appointmentID, 
			patientID).isPresent()) {
			return true;
		}
		return false;
	}
	
	
	
	
	public boolean bookSuccessfully(Appointment appointment, long patientID, String dateOfVisit, String timeOfVisit) {
		if (newBooking(patientID, dateOfVisit, timeOfVisit)) {
			appointmentRepository.save(appointment);
			return true;
		}
		return false;
	}
	
	public boolean cancelSuccessfully(Appointment appointment, long appointmentID, long patientID, String dateOfVisit, String timeOfVisit) {
		if (appointmentExists(appointmentID) && 
			bookedByPatient(appointmentID, patientID) && 
			bookedBefore(patientID, dateOfVisit, timeOfVisit)) {
			appointmentRepository.delete(appointment);
			return true;
		}
		return false;
	}
	
//	public boolean rescheduleSuccessfully(Appointment appointment, long appointmentID, long patientID, String dateOfVisit, String timeOfVisit) {
//		if (appointmentExists(appointmentID) && 
//			bookedByPatient(appointmentID, patientID) && 
//			bookedBefore(patientID, dateOfVisit, timeOfVisit)) {
//			appointmentRepository.delete(appointment);
//			return true;
//		}
//		return false;
//	}
	
}

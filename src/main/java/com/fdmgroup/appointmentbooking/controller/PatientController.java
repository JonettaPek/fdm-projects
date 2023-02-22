package com.fdmgroup.appointmentbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.appointmentbooking.model.Appointment;
import com.fdmgroup.appointmentbooking.model.Patient;
import com.fdmgroup.appointmentbooking.service.CalendarService;
import com.fdmgroup.appointmentbooking.service.PatientService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/my-first-clinic/home-patient")
public class PatientController {

	private final PatientService patientService;
	private final CalendarService calendarService;
	
	@Autowired	
	public PatientController(PatientService patientService, CalendarService calendarService) {
		super();
		this.patientService = patientService;
		this.calendarService = calendarService;
	}
	
	@GetMapping("/")
	public String goToPatientHomePage(HttpSession loginSession,
			Model model) {
		
		String email = (String) loginSession.getAttribute("email");
		String password = (String) loginSession.getAttribute("password");
		
		if (patientService.loginSuccessful(email, password)) {
			
			String username = (String) loginSession.getAttribute("username");
			model.addAttribute("greeting", "Welcome back, " + username + "!");
			
			loginSession.setAttribute("patient", patientService.retrievePatientByEmail(email));
			loginSession.setAttribute("patientID", patientService.retrieveIdByEmail(email));
			
			return "home-patient";
		}
		return "redirect:/my-first-clinic/login";
	}

	@GetMapping("/book-an-appointment")
	public String goToBookingPage() {
		return "new-appointment";
	}
	
	@GetMapping("/view-appointments")
	public String viewAppointments(HttpSession loginSession,
			Model model) {
		long patientID = (Long) loginSession.getAttribute("patientID");
		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
		return "appointments-patient";
	}
	
	@GetMapping("/cancel-an-appointment")
	public String deleteAppointment(HttpSession loginSession,
			Model model) {
		long patientID = (Long) loginSession.getAttribute("patientID");
		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
		return "delete-appointment-patient";
	}
	
	@GetMapping("/reschedule-an-appointment")
	public String rescheduleAppointment(HttpSession loginSession,
			Model model) {
		long patientID = (Long) loginSession.getAttribute("patientID");
		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
		return "reschedule-appointment-patient";
	}
	
	@PostMapping("/book-an-appointment")
	public String handleNewAppointment(@RequestParam("date-of-visit") String dateOfVisit,
			@RequestParam("time-of-visit") String timeOfVisit,
			@RequestParam("location") String location,
			Appointment appointment,
			HttpSession loginSession,
			Model model) {
		
		long patientID = (Long) loginSession.getAttribute("patientID");
		
		appointment.setDateOfVisit(dateOfVisit);
		appointment.setTimeOfVisit(timeOfVisit);
		appointment.setLocation(location);
		appointment.setPatient(patientService.retrievePatientById(patientID));
		
		if (calendarService.bookSuccessfully(appointment, patientID, dateOfVisit, timeOfVisit)) {
			return "redirect:/my-first-clinic/home-patient/";
		}
		return "redirect:book-an-appointment";
	}
	
	@PostMapping("/cancel-an-appointment")
	public String handleCancellation(@RequestParam("appointment-id") String appointmentIdString,
			HttpSession loginSession,
			Model model) {
		System.out.println(appointmentIdString);
		Long appointmentID = Long.parseLong(appointmentIdString);
		
		long patientID = (Long) loginSession.getAttribute("patientID");
		
		Appointment appointment = calendarService.retrieveAppointmentByAppointmentId(appointmentID);
		String dateOfVisit = appointment.getDateOfVisit();
		String timeOfVisit = appointment.getTimeOfVisit();
		
		if (calendarService.cancelSuccessfully(appointment, appointmentID, patientID, dateOfVisit, timeOfVisit)) {
			return "redirect:/my-first-clinic/home-patient/";
		}
		return "redirect:cancel-an-appointment";
	}
	
//	@PostMapping("/reschedule-an-appointment")
//	public String handleRescheduling(@RequestParam("appointment-id") String appointmentIdString,
//			HttpSession loginSession,
//			Model model) {
//		
//		Long appointmentID = Long.parseLong(appointmentIdString);
//		
//		long patientID = (Long) loginSession.getAttribute("patientID");
//		
//		Appointment appointment = calendarService.retrieveAppointmentByAppointmentId(appointmentID);
//		String dateOfVisit = appointment.getDateOfVisit();
//		String timeOfVisit = appointment.getTimeOfVisit();
//		
//		if (calendarService.cancelSuccessfully(appointment, appointmentID, patientID, dateOfVisit, timeOfVisit) &&
//			calendarService.bookSuccessfully(appointment, patientID, dateOfVisit, timeOfVisit)) {
//			return "redirect:/my-first-clinic/home-patient/";
//		}
//		return "redirect:reschedule-an-appointment";
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

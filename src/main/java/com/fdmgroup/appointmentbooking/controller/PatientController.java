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
			
			String[] parsedEmail = email.toUpperCase().split("@");
			String username = parsedEmail[0];
			
			model.addAttribute("greeting", "Welcome back, " + username + "!");
			
			loginSession.setAttribute("patient", patientService.retrievePatientByEmail(email));
			
			
			return "home-patient";
		}
		
		return "redirect:/my-first-clinic/login";
	}

	@GetMapping("/book-an-appointment")
	public String goToBookingPage(HttpSession loginSession) {
		return "new-appointment";
	}
	
	@GetMapping("/view-appointments")
	public String viewAppointments(HttpSession loginSession) {
		
		Patient patient = (Patient) loginSession.getAttribute("patient");
		loginSession.setAttribute("appointments", patient.getAppointments());
		
		return "appointments-patient";
	}
	
	@GetMapping("/cancel-an-appointment")
	public String deleteAppointment(HttpSession loginSession) {
		
		Patient patient = (Patient) loginSession.getAttribute("patient");
		loginSession.setAttribute("appointments", patient.getAppointments());
		
		return "delete-appointment-patient";
	}
	
	@GetMapping("/reschedule-an-appointment")
	public String rescheduleAppointment(HttpSession loginSession) {
		return "";
	}
	
	@PostMapping("/book-an-appointment")
	public String handleNewAppointment(@RequestParam("date-of-visit") String dateOfVisit,
			@RequestParam("time-of-visit") String timeOfVisit,
			@RequestParam("location") String location,
			Appointment appointment,
			HttpSession loginSession) {
		
		appointment.setDateOfVisit(dateOfVisit);
		appointment.setTimeOfVisit(timeOfVisit);
		appointment.setLocation(location);
		appointment.setPatient((Patient) loginSession.getAttribute("patient"));
		
		if (calendarService.bookSuccessfully(appointment)) {
			return "redirect:/my-first-clinic/home-patient/";
		}
		return "redirect:book-an-appointment";
	}
	
	@PostMapping("/cancel-an-appointment")
	public String handleCancellation(@RequestParam("appointment-id") String appointmentIdString,
			HttpSession loginSession) {
		
		Long appointmentID = Long.parseLong(appointmentIdString);
		
		if (calendarService.cancelSuccessfully(appointmentID)) {
			return "redirect:/my-first-clinic/home-patient/";
		}
		
		return "redirect:cancel-an-appointment";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

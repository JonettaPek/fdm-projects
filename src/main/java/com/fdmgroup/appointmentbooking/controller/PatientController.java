package com.fdmgroup.appointmentbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fdmgroup.appointmentbooking.model.Appointment;
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
	
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public String goToPatientHomePage(HttpSession loginSession,
			Model model) {
		String email = (String) loginSession.getAttribute("email");
		String password = (String) loginSession.getAttribute("password");
		if (patientService.loginSuccessful(email, password)) {
			loginSession.setAttribute("loginStatus", "success");
			loginSession.setAttribute("patientID", patientService.retrieveIdByEmail(email));
			model.addAttribute("retrievedPatient", patientService.retrievePatientByEmail(email));
			return "home";
		}
		loginSession.setAttribute("loginStatus", "fail");
		return "redirect:/my-first-clinic/login";
	}

	@GetMapping("/book-an-appointment")
	@ResponseStatus(HttpStatus.OK)
	public String goToBookingPage(Model model) {
		model.addAttribute("appointment", new Appointment());
		return "new-booking";
	}
	
	@PostMapping("/book-an-appointment")
	@ResponseStatus(HttpStatus.CREATED)
	public String handleNewAppointment(Appointment appointment,
			HttpSession loginSession) {
		long patientID = (Long) loginSession.getAttribute("patientID");
		appointment.setPatient(patientService.retrievePatientById(patientID));
		String dateOfVisit = appointment.getDateOfVisit();
		String timeOfVisit = appointment.getTimeOfVisit();
		if (calendarService.bookSuccessfully(appointment, patientID, dateOfVisit, timeOfVisit)) {
			loginSession.setAttribute("bookStatus", "success");
			return "redirect:/my-first-clinic/home-patient/" + loginSession.getAttribute("username");
		}
		return "redirect:book-an-appointment";
	}
	
	@GetMapping("/view-appointments")
	@ResponseStatus(HttpStatus.OK)
	public String viewAppointments(HttpSession loginSession,
			Model model) {
		long patientID = (Long) loginSession.getAttribute("patientID");
		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
		return "view-appointments";
	}
	
	@GetMapping("/cancel-an-appointment")
	@ResponseStatus(HttpStatus.OK)
	public String deleteAppointment(HttpSession loginSession,
			Model model) {
		long patientID = (Long) loginSession.getAttribute("patientID");
		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
		return "delete-appointment";
	}
	
	@PostMapping("/cancel-an-appointment")
	public String handleCancellation(@RequestParam("appointment-id") String appointmentIdString,
			HttpSession loginSession) {
		Long appointmentID = Long.parseLong(appointmentIdString);
		Appointment appointment = calendarService.retrieveAppointmentByAppointmentId(appointmentID);
		String dateOfVisit = appointment.getDateOfVisit();
		String timeOfVisit = appointment.getTimeOfVisit();
		long patientID = (Long) loginSession.getAttribute("patientID");
		if (calendarService.cancelSuccessfully(appointment, appointmentID, patientID, dateOfVisit, timeOfVisit)) {
			loginSession.setAttribute("cancelStatus", "success");
			return "redirect:/my-first-clinic/home-patient/" + loginSession.getAttribute("username");
		}
		return "redirect:cancel-an-appointment";
	}
	
	@GetMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public String logout(HttpSession loginSession) {
		loginSession.invalidate();
		return "redirect:/my-first-clinic/";
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
//	
//	@GetMapping("/reschedule-an-appointment")
//	public String rescheduleAppointment(HttpSession loginSession,
//			Model model) {
//		long patientID = (Long) loginSession.getAttribute("patientID");
//		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
//		return "reschedule-appointment";
//	}

}

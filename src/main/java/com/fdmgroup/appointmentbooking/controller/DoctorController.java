package com.fdmgroup.appointmentbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.appointmentbooking.model.DataTransferObject;
import com.fdmgroup.appointmentbooking.service.CalendarService;
import com.fdmgroup.appointmentbooking.service.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-first-clinic/home-doctor")
public class DoctorController {

	private final DoctorService doctorService;
	private final CalendarService calendarService;

	@Autowired
	public DoctorController(DoctorService doctorService, CalendarService calendarService) {
		super();
		this.doctorService = doctorService;
		this.calendarService = calendarService;
	}
	
	@GetMapping("/{username}")
	public String goToDoctorHomePage(HttpSession loginSession,
			Model model) {
		String email = (String) loginSession.getAttribute("email");
		String password = (String) loginSession.getAttribute("password");
		if (doctorService.loginSuccessful(email, password)) {
			model.addAttribute("retrievedDoctor", doctorService.retrieveDoctorByEmail(email));
			loginSession.setAttribute("doctorID", doctorService.retrieveIdByEmail(email));
			return "home";
		}
		return "redirect:/my-first-clinic/login";
	}
	
//	@GetMapping("/view-appointments")
//	public String viewAppointments(HttpSession loginSession,
//			Model model) {
//		long patientID = (Long) loginSession.getAttribute("patientID");
//		model.addAttribute("appointments", calendarService.retrieveAppointmentsByPatientId(patientID));
//		return "view-appointments";
//	}
	
	@GetMapping("/logout")
	public String logout(HttpSession loginSession) {
		loginSession.invalidate();
		return "redirect:/my-first-clinic/";
	}

}

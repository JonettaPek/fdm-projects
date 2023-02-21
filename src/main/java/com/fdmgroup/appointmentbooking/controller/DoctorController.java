package com.fdmgroup.appointmentbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.appointmentbooking.model.DataTransferObject;
import com.fdmgroup.appointmentbooking.service.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-first-clinic/home-doctor")
public class DoctorController {

	private final DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}
	
	@GetMapping("/")
	public String goToDoctorHomePage(HttpSession loginSession,
			Model model) {
		
		String email = (String) loginSession.getAttribute("email");
		String password = (String) loginSession.getAttribute("password");
		String userType = (String) loginSession.getAttribute("usertype");
		
		if (doctorService.loginSuccessful(email, password)) {
			
			String[] parsedEmail = email.toUpperCase().split("@");
			String username = parsedEmail[0];
			
			model.addAttribute("username", username);
			model.addAttribute("greeting", "Welcome back, Dr. " + username + "!");
			model.addAttribute("doctor", doctorService.retrieveDoctorByEmail(email));
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			model.addAttribute("usertype", userType);
			
			return "home-doctor";
		}
		return "redirect:/my-first-clinic/login";
	}
	
}

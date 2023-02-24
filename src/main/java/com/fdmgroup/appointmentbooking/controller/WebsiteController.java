package com.fdmgroup.appointmentbooking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fdmgroup.appointmentbooking.model.Doctor;
import com.fdmgroup.appointmentbooking.model.Patient;
import com.fdmgroup.appointmentbooking.service.DoctorService;
import com.fdmgroup.appointmentbooking.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-first-clinic")
public class WebsiteController {

	private final PatientService patientService;
	private final DoctorService doctorService;
	
	@Autowired
	public WebsiteController(PatientService patientService, DoctorService doctorService) {
		super();
		this.patientService = patientService;
		this.doctorService = doctorService;
	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String goToMyFirstClinicWebsite() {
		return "my-first-clinic";
	}
	
	@GetMapping("/register-{type}")
	@ResponseStatus(HttpStatus.OK)
	public String goToPatientRegistrationPage(@PathVariable("type") String type,
			Model model,
			HttpSession registerSession) {
		String formattedType = String.valueOf(type.charAt(0)).toUpperCase() + type.substring(1);
		model.addAttribute("type", formattedType);
		model.addAttribute("newPatient", new Patient());
		model.addAttribute("newDoctor", new Doctor());
		model.addAttribute("registerStatus", (String) registerSession.getAttribute("registerStatus"));
		return "registration";
	}
	
	@PostMapping("/register-patient")
	@ResponseStatus(HttpStatus.SEE_OTHER)
	public String handlePatientRegistration(@RequestParam("confirm-password") String confirmPassword,
			Patient patient,
			HttpSession registerSession,
			Model model) {
		String email = patient.getEmail();
		String password = patient.getPassword();
		if (patientService.emailExists(email)) {
			registerSession.setAttribute("email", email);
			registerSession.setAttribute("password", password);
			registerSession.setAttribute("registerStatus", "success");
			registerSession.setAttribute("registerInfoMessage", "Email exists, please log in.");
			return "redirect:login";
		}
		if (patientService.registrationSuccessful(patient, email, password, confirmPassword)) {
			registerSession.setAttribute("email", email);
			registerSession.setAttribute("password", password);
			registerSession.setAttribute("registerStatus", "success");
			registerSession.setAttribute("registerInfoMessage", "Register successfully, please log in.");
			return "redirect:login";
		}
		registerSession.setAttribute("registerStatus", "fail");
		return "redirect:register-patient";
	}
	
	@PostMapping("/register-doctor")
	@ResponseStatus(HttpStatus.SEE_OTHER
			)
	public String handleDoctorRegistration(@RequestParam("confirm-password") String confirmPassword,
			Doctor doctor,
			HttpSession registerSession) {
		String email = doctor.getEmail();
		String password = doctor.getPassword();
		if (doctorService.emailExists(email)) {
			registerSession.setAttribute("email", email);
			registerSession.setAttribute("password", password);
			registerSession.setAttribute("registerStatus", "success");
			registerSession.setAttribute("registerInfoMessage", "Email exists, please log in.");
			return "redirect:login";
		}
		if (doctorService.registrationSuccessful(doctor, email, password, confirmPassword)) {
			registerSession.setAttribute("email", email);
			registerSession.setAttribute("password", password);
			registerSession.setAttribute("registerStatus", "success");
			registerSession.setAttribute("registerInfoMessage", "Register successfully, please log in.");
			return "redirect:login";
		}
		registerSession.setAttribute("registerStatus", "fail");
		return "redirect:register-doctor";
		
	}
	
	@GetMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public String goToLoginPage(HttpSession registerSession,
			Model model) {
		model.addAttribute("email", (String) registerSession.getAttribute("email"));
		model.addAttribute("password", (String) registerSession.getAttribute("password"));
		model.addAttribute("registerStatus", (String) registerSession.getAttribute("registerStatus"));
		model.addAttribute("registerInfoMessage", (String) registerSession.getAttribute("registerInfoMessage"));
		model.addAttribute("loginStatus", (String) registerSession.getAttribute("loginStatus"));
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.SEE_OTHER)
	public String handleLogin(@RequestParam("e-mail") String email,
			@RequestParam("password") String password,
			@RequestParam("user-type") String userType,
			HttpSession loginSession) {
		String[] parsedEmail = email.split("@");
		String username = parsedEmail[0];
		loginSession.setAttribute("username", username);
		loginSession.setAttribute("email", email);
		loginSession.setAttribute("password", password);
		loginSession.setAttribute("usertype", userType);
		if (userType.equalsIgnoreCase("patient")) {
			return "redirect:/my-first-clinic/home-patient/" + username;
		}
		return "redirect:/my-first-clinic/home-doctor/" + username;
	}
}

package com.fdmgroup.appointmentbooking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String goToMyFirstClinicWebsite() {
		return "my-first-clinic";
	}
	
	@GetMapping("/register-patient")
	public String goToPatientRegistrationPage() {
		return "patient-registration";
	}
	
	@GetMapping("/register-doctor")
	public String goToDoctorRegistrationPage() {
		return "doctor-registration";
	}
	
	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}
	
	@PostMapping("/register-patient")
	public String handlePatientRegistration(@RequestParam("first-name") String firstName,
			@RequestParam("last-name") String lastName,
			@RequestParam("address") String address,
			@RequestParam("date-of-birth") String dateOfBirth,
			@RequestParam("e-mail") String email, 
			@RequestParam("password") String password, 
			@RequestParam("confirm-password") String confirmPassword,
			Patient patient) {
		
		patient.setFirstName(firstName.toUpperCase());
		patient.setLastName(lastName.toUpperCase());
		patient.setAddress(address);
		patient.setDateOfBirth(dateOfBirth);
		patient.setEmail(email);
		patient.setPassword(password);
		
		if (patientService.emailExists(email) 
				|| patientService.registrationSuccessful(patient, email, password, confirmPassword)) {
			
			return "redirect:login";
		}
		
		return "redirect:register-patient";
	}
	
	@PostMapping("/register-doctor")
	public String handleDoctorRegistration(@RequestParam("first-name") String firstName,
			@RequestParam("last-name") String lastName,
			@RequestParam("e-mail") String email,
			@RequestParam("password") String password,
			@RequestParam("confirm-password") String confirmPassword,
			@RequestParam("specialty") String specialty,
			Doctor doctor) {
		
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setEmail(email);
		doctor.setPassword(password);
		doctor.setSpecialty(specialty);
		
		if (doctorService.emailExists(email)
				|| doctorService.registrationSuccessful(doctor, email, password, confirmPassword)) {
			return "redirect:login";
		}
		return "redirect:register-doctor";
		
	}
	
	@PostMapping("/login")
	public String handleLogin(@RequestParam("e-mail") String email,
			@RequestParam("password") String password,
			@RequestParam("user-type") String userType,
			HttpSession loginSession) {
		
		loginSession.setAttribute("email", email);
		loginSession.setAttribute("password", password);
		loginSession.setAttribute("usertype", userType);

		if (userType.equals("patient")) {
			
			return "redirect:/my-first-clinic/home-patient/";
		}
		return "redirect:/my-first-clinic/home-doctor/";
		

	}
}

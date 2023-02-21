package com.fdmgroup.appointmentbooking.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.appointmentbooking.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findAllByDateOfVisit(String dateOfVisit);

	public List<Appointment> findAllByTimeOfVisit(String timeOfVisit);
	
	@Query("SELECT a FROM Appointment a WHERE a.patient LIKE :patientID")
	public List<Appointment> findAllByPatientId(@Param("patientID") long patientID);

	@Query("SELECT a FROM Appointment a WHERE a.doctor LIKE :doctorID")
	public List<Appointment> findAllByDoctorId(@Param("doctorID") long doctorID);
	
	@Query("SELECT a FROM Appointment a WHERE a.timeslot LIKE :timeslotID")
	public List<Appointment> findAllByTimeslotId(@Param("timeslotID") long timeslotID);
	
	@Query("SELECT a FROM Appointment a WHERE a.timeslot IS NULL")
	public List<Appointment> findAllByTimeslotIdIsNull();
	
	
	
	@Query("SELECT a FROM Appointment a WHERE a.patient LIKE :patientID AND " + 
			"a.dateOfVisit LIKE :dateOfVisit")
	public List<Appointment> findAllByPatientIdAndDateOfVisit(@Param("patientID") long patientID, 
			@Param("dateOfVisit") String dateOfVisit);
	
	@Query("SELECT a FROM Appointment a WHERE a.patient LIKE :patientID AND " + 
			"a.timeOfVisit LIKE :timeOfVisit")
	public List<Appointment> findAllByPatientIdAndTimeOfVisit(@Param("patientID") long patientID, 
			@Param("timeOfVisit") String timeOfVisit);
	
	@Query("SELECT a FROM Appointment a WHERE a.patient LIKE :patientID AND " +
			"a.dateOfVisit LIKE :dateOfVisit AND " +
			"a.timeOfVisit LIKE :timeOfVisit")
	public Optional<Appointment> findByPatientIdAndDateOfVisitAndTimeOfVisit(@Param("patientID") long patientID, 
			@Param("dateOfVisit") String dateOfVisit,
			@Param("timeOfVisit") String timeOfVisit);
	
	
	
	@Query("SELECT a FROM Appointment a WHERE a.doctor LIKE :doctorID AND " + 
			"a.dateOfVisit LIKE :dateOfVisit")
	public List<Appointment> findAllByDoctorIdAndDateOfVisit(@Param("doctorID") long doctorID, 
			@Param("dateOfVisit") String dateOfVisit);
	
	@Query("SELECT a FROM Appointment a WHERE a.doctor LIKE :doctorID AND " + 
			"a.timeOfVisit LIKE :timeOfVisit")
	public List<Appointment> findAllByDoctorIdAndTimeOfVisit(@Param("doctorID") long doctorID, 
			@Param("timeOfVisit") String timeOfVisit);
	
	@Query("SELECT a FROM Appointment a WHERE a.doctor LIKE :doctorID AND " +
			"a.dateOfVisit LIKE :dateOfVisit AND " +
			"a.timeOfVisit LIKE :timeOfVisit")
	public Optional<Appointment> findByDoctorIdAndDateOfVisitAndTimeOfVisit(@Param("doctorID") long doctorID, 
			@Param("dateOfVisit") String dateOfVisit,
			@Param("timeOfVisit") String timeOfVisit);
	
	
	@Query("SELECT a FROM Appointment a WHERE a.timeOfVisit LIKE :timeOfVisit AND a.dateOfVisit LIKE :dateOfVisit")
	public List<Appointment> findAllByTimeOfVisitAndDateOfVisit(@Param("timeOfVisit") String timeOfVisit, 
			@Param("dateOfVisit") String dateOfVisit);
}

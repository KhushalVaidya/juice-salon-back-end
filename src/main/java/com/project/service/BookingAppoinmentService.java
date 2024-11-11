package com.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.model.BookingAppointment;
import com.project.repository.BookingAppointmentRepository;


@Service
public class BookingAppoinmentService {

	@Autowired BookingAppointmentRepository BookingAppoResp;
	
//	public BookingAppointment BookingDone(BookingAppointment appointment) {		
//	return BookingAppoResp.save(appointment);
//		
//	}
	
	public BookingAppointment bookingDone(BookingAppointment appointment) {
	    if (appointment == null) {
	        throw new IllegalArgumentException("Appointment data cannot be null");
	    }
	    
	    try {
	        return BookingAppoResp.save(appointment);
	    } catch (Exception e) {
	        System.err.println("Error saving appointment: " + e.getMessage());
	        throw new RuntimeException("Failed to book the appointment. Please try again later.");
	    }
	}


	
	
	public String BookingDeletedById(String appoid) {
		// TODO Auto-generated method stub
		Optional<BookingAppointment> appointmentId=BookingAppoResp.findById(appoid);
		if(appointmentId.isPresent()) {

		return "Appointment Cancelled Successfully";
	}else {
		return "Appointment does not Exist";
	}}

	

	public void AllBookingsDeleted() {
		// TODO Auto-generated method stub
		BookingAppoResp.deleteAll();
//		return "All Bookings Cancelled";
		
	}

	public Optional<BookingAppointment> GetAppointmentDetailsByid(String appoid) {
		return BookingAppoResp.findById(appoid);	
	}

	

	public Optional<BookingAppointment> RescheduleAppointment(String appoid, LocalDateTime appointmentTime) {
		return BookingAppoResp.findById(appoid).map(appointment->{
			appointment.setAppointmentTime(appointmentTime);
			return BookingAppoResp.save(appointment);
		});	
	}

	
	public List<BookingAppointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return BookingAppoResp.findAll();	
	}

	
	
	

}

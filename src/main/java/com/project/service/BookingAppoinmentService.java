package com.project.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import com.project.model.BookingAppointment;
import com.project.repository.BookingAppointmentRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class BookingAppoinmentService {

	@Autowired BookingAppointmentRepository BookingAppoResp;
	
	@Autowired 	JavaMailSender sender;
	
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


	public void sendingMail(String firstName, String to,LocalDateTime appointment) throws MessagingException,IOException {
		
		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 simpleMailMessage.setTo(to);
		 
		 String subject = "Appointment Booked Successfully!!! ";
		 
		 simpleMailMessage.setSubject(subject);
		 

		    // Format the LocalDateTime
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		    String formattedAppointmentTime = appointment.format(formatter);
		 
		 String body = "Hi " + firstName + ","
	                + "\n\nThank you for booking an appointment with Juice Salon!"
	                + "\nYour appointment has been successfully scheduled, and we’re excited to provide you with our premium salon services."
	                + "\nPlease check your appointment details and arrive a few minutes early to ensure a seamless experience."
	                +formattedAppointmentTime
	                + "\n\nBest regards,\nTeam Juice Salon"
	                + "\n\n\n*** Please note that this is an automatically generated email that cannot receive replies ***";

		 MimeMessage mime = sender.createMimeMessage();
		 MimeMessageHelper mimeHelper = new MimeMessageHelper(mime);
		 mimeHelper.setTo(to);
		 mimeHelper.setSubject(subject);
		 mimeHelper.setText(body);
		 
		 sender.send(mime);
		 
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

package com.project.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.BookingAppointment;
import com.project.service.BookingAppoinmentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/BookAppointment")
@CrossOrigin("*")
public class BookingAppoinmentController {
@Autowired BookingAppoinmentService bookAppoService;
	


//
//@PostMapping("/Book")
//public ResponseEntity<BookingAppointment> BookingDone(@RequestBody BookingAppointment appointment) {
//	BookingAppointment BookedAppointment=bookAppoService.BookingDone(appointment);
//	
//	return ResponseEntity.ok(BookedAppointment);
//		
//	}
@PostMapping("/Book")
public ResponseEntity<?> bookingDone(@Valid @RequestBody BookingAppointment appointment) {
    try {
        BookingAppointment bookedAppointment = bookAppoService.bookingDone(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookedAppointment);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid appointment data: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to book the appointment. Please try again later.");
    }
}




	@DeleteMapping("/delete")
	public ResponseEntity<String>BookingDeletedById(@RequestParam("Appoid") String Appoid){
		
		return ResponseEntity.ok(bookAppoService.BookingDeletedById(Appoid)); 
		
	}
	
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> AllBookingsDeleted(){
	bookAppoService.AllBookingsDeleted();	
		return ResponseEntity.ok("All Appointments Cancelled Successfully");
	}
	
	
	@GetMapping("/getAppointmentById")
	public Optional <BookingAppointment> GetAppointmentDetailsByid(@RequestParam("Appoid") String Appoid){
		Optional< BookingAppointment> details=bookAppoService.GetAppointmentDetailsByid(Appoid);
		return details;
	}
	
	
	@PutMapping("/Reschedule")
	public ResponseEntity<BookingAppointment> RescheduleAppointment(@RequestParam("Appoid") String Appoid,@RequestBody BookingAppointment appointment){
		Optional<BookingAppointment> Reschedule=bookAppoService.RescheduleAppointment(Appoid,appointment.getAppointmentTime());
		return Reschedule.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@GetMapping("/getAllAppointments")
	public ResponseEntity<List<BookingAppointment>> getAllAppointments() {
		List<BookingAppointment> appointments=bookAppoService.getAllAppointments();
		return ResponseEntity.ok(appointments);
	}
	
}

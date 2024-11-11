package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.BookingAppointment;


@Repository
public interface BookingAppointmentRepository extends JpaRepository<BookingAppointment,String>{
	
	
	

}

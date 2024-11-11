package com.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class BookingAppointment {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "appoid", nullable = false, updatable = false)
    private String appoId;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;



//    @NotNull(message = "Appointment time is required")
    private LocalDateTime appointmentTime;

    private String service;

    
}

//public class BookingAppointment {
//	
//	
//	private String FirstName;
//	private String LastName;
//	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
//	private String Appoid;
//	private String Email;
//	private Long ContactNumber;
//	private LocalDateTime AppointmentTime;
//	private String Service;
//
//
//}



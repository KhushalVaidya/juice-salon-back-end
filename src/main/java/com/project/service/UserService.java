package com.project.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.model.userRegister;
import com.project.repository.UserRepository;
import com.project.serviceInterface.userInterface;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService implements userInterface{

	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	JavaMailSender sender;
	
	

	public void registerMail(String Name, String to) throws MessagingException,IOException {
		
		 SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		 simpleMailMessage.setTo(to);
		 
		 String subject = "Successfully!!! Registered";
		 
		 simpleMailMessage.setSubject(subject);
		 
		 String body = "Hi " + Name + ","
	                + "\n\nThank you for registering on Juice Salon!"
	                + "\nYour registration was successful, and we’re thrilled to have you with us."
	                + "\nGet ready to explore our range of premium salon services."
	                + "\n\nBest regards,\nTeam Juice Salon"
	                + "\n\n\n*** Please note that this is an automatically generated email that cannot receive replies ***";

		 
		 MimeMessage mime = sender.createMimeMessage();
		 MimeMessageHelper mimeHelper = new MimeMessageHelper(mime);
		 mimeHelper.setTo(to);
		 mimeHelper.setSubject(subject);
		 mimeHelper.setText(body);
		 
		 sender.send(mime);
		 
	}

	public ResponseEntity<?>  register(userRegister data) {
		// TODO Auto-generated method stub
		if(data != null) {
			userRepository.save(data);
			return new ResponseEntity<>("data saved", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
		
	}

	public void deleteAccount(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}
	
	
	
	

	public ResponseEntity<?> userLogin(String mobileNumber, String password) {
		
		userRegister user = userRepository.findByMobileNumber(mobileNumber);
		System.out.println("obect of user :" + user);
		
		String mobileCheck = user.getMobileNumber();
		
		try {
			
			if(user != null) {
				if(mobileCheck.equals(mobileNumber) && password.equals(user.getPassword())) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			
			return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<>("invalid credentials", HttpStatus.BAD_REQUEST);
	}
	
	
}

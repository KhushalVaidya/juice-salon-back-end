package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.model.userRegister;
import com.project.repository.UserRepository;
import com.project.serviceInterface.userInterface;

@Service
public class UserService implements userInterface{

	@Autowired 
	UserRepository userRepository;

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

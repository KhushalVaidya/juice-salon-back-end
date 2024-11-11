package com.project.serviceInterface;

import org.springframework.http.ResponseEntity;

import com.project.Dto.SignUpDto;
import com.project.model.userRegister;



public interface userInterface {

//	public  SignUpDto signUpUser(SignUpDto signUp);
	
	public ResponseEntity<?> userLogin(String mobileNumber, String password);
}

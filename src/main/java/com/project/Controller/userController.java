package com.project.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.SignUpDto;
import com.project.model.BookingAppointment;
import com.project.model.Response;
import com.project.model.userRegister;
import com.project.repository.UserRepository;
import com.project.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class userController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody userRegister user) {
	    return userService.register(user);
	}
	
		
	
	@DeleteMapping("/deleteAccount")
	public ResponseEntity<String> deleteAccount(@RequestParam("id") Long userId) {
	
	Optional<userRegister> user =userRepository.findById(userId);
	
	try {
		if(user.isPresent()) {
			userService.deleteAccount(userId);
			return new ResponseEntity<>("Account Has been deleted Successfully",HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>("Account not found",HttpStatus.BAD_GATEWAY);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
		return new ResponseEntity<>("bad request",HttpStatus.BAD_GATEWAY);

	}
	
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("password") String password) {
		return userService.userLogin(mobileNumber, password);
	}


  

}
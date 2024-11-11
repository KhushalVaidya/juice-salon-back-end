package com.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.userRegister;

public interface UserRepository extends JpaRepository<userRegister, Long> {
	
	public userRegister findByMobileNumber(String mobileNumber);
	
}

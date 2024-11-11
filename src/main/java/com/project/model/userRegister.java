package com.project.model;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class userRegister{
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="mobileNumber")
	private String mobileNumber;
	
	@JsonProperty("Name")
	private String UserName;
	private String Email;
	private String Password;
	
	
}

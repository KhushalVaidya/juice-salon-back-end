package com.project.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

	private Long MobileNumber;
	@JsonProperty("Name")
	private String UserName;
	private String Email;
	private String Password;
	
}

package com.taskmonitoring.system.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
	@NotNull(message="Full name is required")
	@NotBlank(message="please insert value")
	@Size(min=3,max=20,message="fullName minimum 3 content and maximum 20")
	private String fullName;
	@NotNull(message="Email is required")
	@NotBlank(message="please insert value")
	@Size(min=10,max=50,message="email minimum 10 content and maximum 30")
	private String email;
	
	@NotNull(message="Username is required")
	@NotBlank(message="please insert value")
	@Size(min=3,max=20,message="Username minimum 3 content and maximum 20")
	private String username;
	@NotNull(message="Password is required")
	@NotBlank(message="please insert value")
	@Size(min=5,max=15,message="password minimum 5 content and maximum 15")
	private String password;
//	private int otp;
}

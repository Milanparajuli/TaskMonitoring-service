package com.taskmonitoring.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
	private String fullName;
	private String email;
	private String username;
	private String password;
//	private int otp;
}

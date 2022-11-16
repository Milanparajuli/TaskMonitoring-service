package com.taskmonitoring.system.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserListResponseDto {
	private List<UserResponseDto> users;
	private int total;
}

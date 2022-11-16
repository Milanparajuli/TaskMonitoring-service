package com.taskmonitoring.system.dto;

import lombok.Data;

@Data
public class TaskCreateDto {
	private Long userId;
	private String task;

}

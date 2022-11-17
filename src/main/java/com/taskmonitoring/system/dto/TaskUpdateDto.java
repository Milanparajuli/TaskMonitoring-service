package com.taskmonitoring.system.dto;

import com.taskmonitoring.system.enumtype.TaskStatus;

import lombok.Data;

@Data
public class TaskUpdateDto extends TaskCreateDto {
	protected Long id;
	protected TaskStatus taskStatus;
}

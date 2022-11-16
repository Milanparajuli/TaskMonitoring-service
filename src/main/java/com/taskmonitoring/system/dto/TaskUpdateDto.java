package com.taskmonitoring.system.dto;

import lombok.Data;

@Data
public class TaskUpdateDto extends TaskCreateDto {
	protected Long id;
}

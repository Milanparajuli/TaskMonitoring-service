package com.taskmonitoring.system.dto;

import java.util.List;
import lombok.Data;

@Data
public class TaskResponseListDto {
	private List<TaskResponseDto> task;
	private int total;
}

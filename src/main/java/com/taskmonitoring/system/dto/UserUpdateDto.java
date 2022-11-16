package com.taskmonitoring.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto extends UserCreateDto{
	protected Long id;

}

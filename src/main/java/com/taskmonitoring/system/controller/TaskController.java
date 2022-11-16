package com.taskmonitoring.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taskmonitoring.system.dto.TaskCreateDto;
import com.taskmonitoring.system.dto.TaskResponseDto;
import com.taskmonitoring.system.dto.TaskResponseListDto;
import com.taskmonitoring.system.dto.TaskUpdateDto;
import com.taskmonitoring.system.entity.Task;
import com.taskmonitoring.system.service.UserService;

@RestController
@RequestMapping("api/task")
public class TaskController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public TaskResponseDto addTask(@RequestBody TaskCreateDto task) {
		return userService.addTask(task);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public TaskResponseListDto getAllTask() {
		return userService.getAllTask();
	}
	
	@GetMapping("/by-id/{id}")
	@ResponseStatus(code= HttpStatus.OK)
	public TaskResponseDto getTask(@PathVariable Long id) {
		return userService.getTask(id);
	}
	
	@PutMapping("by-id/{id}")
	@ResponseStatus(code= HttpStatus.OK)
	public Task update(@PathVariable Long id, @RequestBody TaskUpdateDto request) {
		return userService.updateTask(id,request);
	}
	
	@DeleteMapping("by-id/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		userService.deleteById(id);
	}
	
}

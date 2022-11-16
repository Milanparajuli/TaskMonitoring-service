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

import com.taskmonitoring.system.dto.LoginRequestDto;
import com.taskmonitoring.system.dto.LogoutRequestDto;
import com.taskmonitoring.system.dto.UserCreateDto;
import com.taskmonitoring.system.dto.UserListResponseDto;
import com.taskmonitoring.system.dto.UserResponseDto;
import com.taskmonitoring.system.dto.UserUpdateDto;
import com.taskmonitoring.system.entity.User;
import com.taskmonitoring.system.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseDto addUser(@RequestBody UserCreateDto request) {
//		System.out.println("Called");

		return userService.addUser(request);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public UserListResponseDto getAll() {
//		System.out.println("Get all method called");
		return userService.getAll();
	}

	@PutMapping("by-id/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public User update(@PathVariable Long id, @RequestBody UserUpdateDto request) {

		return userService.update(id, request);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		userService.delete(id);

	}

	@DeleteMapping("/by-name/{name}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteByName(@PathVariable String name) {
		userService.deleteByName(name);

	}

	@GetMapping("/by-id/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserResponseDto getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String login(@RequestBody LoginRequestDto request) {
		return userService.login(request);

	}
	@PostMapping("logout")
	public String logout(@RequestBody LogoutRequestDto request) {
		return userService.logout(request);
	}

}

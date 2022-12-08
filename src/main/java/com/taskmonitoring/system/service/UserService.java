package com.taskmonitoring.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmonitoring.system.dto.LoginRequestDto;
import com.taskmonitoring.system.dto.LoginResponseDto;
import com.taskmonitoring.system.dto.LogoutRequestDto;
import com.taskmonitoring.system.dto.LogoutResponseDto;
import com.taskmonitoring.system.dto.TaskCreateDto;
import com.taskmonitoring.system.dto.TaskResponseDto;
import com.taskmonitoring.system.dto.TaskResponseListDto;
import com.taskmonitoring.system.dto.TaskUpdateDto;
import com.taskmonitoring.system.dto.UserCreateDto;
import com.taskmonitoring.system.dto.UserListResponseDto;
import com.taskmonitoring.system.dto.UserResponseDto;
import com.taskmonitoring.system.dto.UserUpdateDto;
import com.taskmonitoring.system.entity.Task;
import com.taskmonitoring.system.entity.User;
import com.taskmonitoring.system.enumtype.TaskStatus;
import com.taskmonitoring.system.repository.TaskRepository;
import com.taskmonitoring.system.repository.UserRepository;
import com.taskmonitoring.system.security.BCrypt;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	public UserResponseDto addUser(UserCreateDto request) {
		User user = new User();
		String originalPassword = request.getPassword();
		String encryptedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());

		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPassword(encryptedPassword);
//		user.setOtp(request.getOtp());
		User savedUser = userRepository.save(user);

		UserResponseDto response = new UserResponseDto();
		response.setEmail(savedUser.getEmail());
		response.setFullName(savedUser.getFullName());
		response.setUsername(savedUser.getUsername());
		response.setPassword(savedUser.getPassword());
//		response.setOtp(savedUser.getOtp());
		response.setId(savedUser.getUserId());
		return response;
	}

	private UserResponseDto getUserResponse(User saveUser) {
		UserResponseDto response = new UserResponseDto();
		response.setEmail(saveUser.getEmail());
		response.setUsername(saveUser.getUsername());
		response.setFullName(saveUser.getFullName());
		response.setPassword(saveUser.getPassword());
		response.setId(saveUser.getUserId());
		return response;

	}

	public UserListResponseDto getAll() {
		List<UserResponseDto> userResponseList = new ArrayList<>();
		List<User> users = (List<User>) userRepository.findAll();
		for (User user : users) {
			userResponseList.add(getUserResponse(user));
		}
		UserListResponseDto response = new UserListResponseDto();
		response.setUsers(userResponseList);
		response.setTotal(userResponseList.size());
		return response;
	}

	public User update(Long id, UserUpdateDto request) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();

			String originalPassword = user.getPassword();
			String encryptedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());

			user.setEmail(request.getEmail());
			user.setFullName(request.getFullName());
			user.setUsername(request.getUsername());
			user.setPassword(encryptedPassword);
//			user.setOtp(request.getOtp());
			return userRepository.save(user);
		}
		return null;

	}

	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Transactional
	public void deleteByName(String name) {
		userRepository.deleteByFullName(name);
	}

	public TaskResponseDto addTask(TaskCreateDto request) {
		Task tasks = new Task();
		tasks.setTask(request.getTask());

		User user = new User();
		user.setUserId(request.getUserId());

		tasks.setUser(user);

		tasks.setStatus(TaskStatus.TODO);

		Task savedTask = taskRepository.save(tasks);
		return getTaskResponse(savedTask);
	}

	private TaskResponseDto getTaskResponse(Task saveTask) {
		User users = new User();
		users.setUserId(users.getUserId());
		System.out.println(saveTask.getId());
		TaskResponseDto response = new TaskResponseDto();
		response.setTask(saveTask.getTask());
		response.setId(saveTask.getId());
		response.setUserId(saveTask.getUser().getUserId());
		response.setTaskStatus(saveTask.getTaskStatus());
		return response;

	}

	public TaskResponseListDto getAllTask() {
		List<TaskResponseDto> taskResponseList = new ArrayList<>();
		List<Task> tasks = (List<Task>) taskRepository.findAll();
		for (Task task : tasks) {
			taskResponseList.add(getTaskResponse(task));
			;
		}
		TaskResponseListDto response = new TaskResponseListDto();
		response.setTask(taskResponseList);
		response.setTotal(taskResponseList.size());
		return response;
	}

	public Task updateTask(Long id, TaskUpdateDto request) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			Task tasks = optional.get();
			tasks.setTask(request.getTask());
			tasks.setStatus(request.getTaskStatus());
			return taskRepository.save(tasks);
		}
		return null;
	}

	public TaskResponseDto getTask(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		if (optionalTask.isPresent()) {
			return getTaskResponse(optionalTask.get());
		}

		return null;
	}
	
	public TaskResponseListDto getTaskByUserId(Long id) {
		List<TaskResponseDto> taskResponseList = new ArrayList<>();
		List<Task> taskList = taskRepository.findByUserUserId(id);
		
		for (Task task : taskList) {
			taskResponseList.add(getTaskResponse(task));
			;
		}
		TaskResponseListDto response = new TaskResponseListDto();
		response.setTask(taskResponseList);
		response.setTotal(taskResponseList.size());
		return response;
//		return null;
	}

	@Transactional
	public void deleteById(Long id) {
		taskRepository.deleteById(id);
	}

	public UserResponseDto getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return getUserResponse(optionalUser.get());
		}
		return null;
	}

	public LoginResponseDto login(LoginRequestDto request) {
		
		LoginResponseDto response = new LoginResponseDto();
		User user = userRepository.findByUsername(request.getUsername());
		if (user == null) {
			throw new RuntimeException("User Dosent exist");
		}

		boolean checkpw = BCrypt.checkpw(request.getPassword(), user.getPassword());
		if (!checkpw) {
			throw new RuntimeException("Invalid Password. please type your correct password.");
		}
		user.setLoggedIn(true);
		userRepository.save(user);
		
		
		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		
		return response;
		
	}

	public LogoutResponseDto logout(LogoutRequestDto request) {
		User user = userRepository.findByUsername(request.getUsername());
		
		LogoutResponseDto response = new LogoutResponseDto();
		if (user == null) {
			response.setMessage("User Dose not exist. please register first.");
			return response;
		}
		user.setLoggedIn(false);
		userRepository.save(user);
		return response;
	}

}

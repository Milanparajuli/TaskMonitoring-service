package com.taskmonitoring.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmonitoring.system.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUserUserId(Long id);

}

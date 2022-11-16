package com.taskmonitoring.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmonitoring.system.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

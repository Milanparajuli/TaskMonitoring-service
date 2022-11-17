package com.taskmonitoring.system.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taskmonitoring.system.enumtype.TaskStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String task;
//enum status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	User user;
	
	private int status;
	
	public void setStatus(TaskStatus taskStatus) {
		this.status = taskStatus.getValue();
	}
	
	public TaskStatus getTaskStatus() {
		return TaskStatus.getTaskStatus(status);
	}
	
}

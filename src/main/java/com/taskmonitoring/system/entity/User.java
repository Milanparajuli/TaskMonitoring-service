package com.taskmonitoring.system.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity 
@Table(name= "user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable=false)
	private String fullName;
	private String email;
	private String username;
	private String password;
	private boolean loggedIn;
//	private int otp;
	@JsonIgnore
	@OneToMany(mappedBy = "user") 
	List<Task> tasks;
	
}

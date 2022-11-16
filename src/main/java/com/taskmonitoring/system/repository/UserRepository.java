package com.taskmonitoring.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.taskmonitoring.system.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
// delete from user where fullName=?
	void deleteByFullName(String name);

	User findByUsername(String username);

}

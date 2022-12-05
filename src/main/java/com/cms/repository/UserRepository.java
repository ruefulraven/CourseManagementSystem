package com.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserEmail(String email);
	List<User> findByUserType(String userType);
}

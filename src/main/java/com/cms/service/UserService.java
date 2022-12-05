package com.cms.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cms.dto.UserDTO;
import com.cms.entity.Student;
import com.cms.entity.User;

public interface UserService extends UserDetailsService{

	User save(UserDTO userDTO);
	List<User> showStudent();
}

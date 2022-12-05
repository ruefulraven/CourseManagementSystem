package com.cms.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.dto.UserDTO;
import com.cms.entity.Role;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Autowired
	@Lazy
	public BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User save(UserDTO userDTO) {
		userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
		userDTO.setRoles(Arrays.asList(new Role("ROLE_USER")));
		User user = new User(userDTO);
		repository.save(user);
		return user;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = repository.findByUserEmail(username);
		if(user == null) throw new UsernameNotFoundException("Invalid Username or Password");
		
		return new org.springframework.security.core.userdetails.User(user.getFirstName()+ " " + user.getLastName(), user.getUserPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<User> showStudent() {
		return repository.findByUserType("ROLE_USERS");
	}

	

}

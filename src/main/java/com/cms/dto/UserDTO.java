package com.cms.dto;

import java.util.Collection;

import javax.persistence.Column;

import com.cms.entity.Role;
import com.cms.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDTO {

	private Integer userID;
	private String uniqueID;
	private String userName;
	private String userControl;
	private String userType;
	private String userEmail;
	private String userPassword;
	private String firstName;
	private String lastName;
	private Collection<Role> roles;
	
	public UserDTO(User user) {
		this.userID = user.getUserID();
		this.userName = user.getUserName();
		this.userControl = user.getUserControl();
		this.userType = user.getUserType();
		this.userEmail = user.getUserEmail();
		this.userPassword = user.getUserPassword();
		this.lastName = user.getLastName();
		this.firstName = user.getFirstName();
		this.roles = user.getRoles();
		this.uniqueID = user.getUniqueID();
	}
	
}

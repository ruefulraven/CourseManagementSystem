package com.cms.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cms.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer userID;
	
	@Column(name = "unqID")
	private String uniqueID;
	
	@Column(name = "name")
	private String userName;
	
	@Column(name = "control")
	private String userControl;
	
	@Column(name = "type")
	private String userType;
	
	@Column(name = "email")
	private String userEmail;
	
	@Column(name = "password")
	private String userPassword;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
					joinColumns = @JoinColumn(
							name = "user_id", referencedColumnName = "id"), 
					inverseJoinColumns = @JoinColumn(
							name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	public User(UserDTO user) {
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

package com.cms.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cms.dto.StudentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer studentNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String schoolYear;
	private String cellphoneNumber;
	private String studentSection;
	
	public Student(StudentDTO studentDTO) {
		this.id = studentDTO.getId();
		this.studentNumber = studentDTO.getStudentNumber();
		this.firstName = studentDTO.getFirstName();
		this.middleName = studentDTO.getMiddleName();
		this.lastName = studentDTO.getLastName();
		this.schoolYear = studentDTO.getSchoolYear();
		this.cellphoneNumber = studentDTO.getCellphoneNumber();
		this.studentSection = studentDTO.getStudentSection();
	}

}

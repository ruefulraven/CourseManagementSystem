package com.cms.dto;

import com.cms.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class StudentDTO {

	private Integer id;
	private Integer studentNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String schoolYear;
	private String cellphoneNumber;
	private String studentSection;
	
	public StudentDTO(Student student) {
		this.id = student.getId();
		this.studentNumber = student.getStudentNumber();
		this.firstName = student.getFirstName();
		this.middleName = student.getMiddleName();
		this.lastName = student.getLastName();
		this.schoolYear = student.getSchoolYear();
		this.cellphoneNumber = student.getCellphoneNumber();
		this.studentSection = student.getStudentSection();
	}
}

package com.cms.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Curriculum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer curriculumID;
	//private List<String> subjectCodes;
	//private List<String> studentCodes;
	private String curriculumName;
	private Integer instructorCode;
	
}
	

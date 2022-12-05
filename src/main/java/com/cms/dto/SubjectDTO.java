package com.cms.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SubjectDTO {

	private Integer id;
	private Integer subjectCode;
	private String subjectName;
	private Collection<StudentDTO> students;
	private Collection<ScheduleDTO> subjectSchedule;
}

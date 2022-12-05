package com.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class StudentRequest {

	StudentDTO student;
	SubjectDTO subject;
	ScheduleDTO schedule;
}

package com.cms.entity;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubjectSchedule {
	
	private Collection<String> subjectDay;
	private Collection<String> subjectTime;
}

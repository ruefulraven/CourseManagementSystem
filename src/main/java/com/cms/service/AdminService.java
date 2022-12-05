package com.cms.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cms.entity.Curriculum;
import com.cms.entity.Student;
import com.cms.entity.Subject;


public interface AdminService {

	public Curriculum createCurriculum(Curriculum curriculum);
	public Subject encodeSubject(Subject subject);
	public Student createStudent(Student student);
	
}

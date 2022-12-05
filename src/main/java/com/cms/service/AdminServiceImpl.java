package com.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cms.entity.Curriculum;
import com.cms.entity.Student;
import com.cms.entity.Subject;
import com.cms.repository.AdminRepository;
import com.cms.repository.StudentRepository;
import com.cms.repository.SubjectRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Subject encodeSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public Curriculum createCurriculum(Curriculum curriculum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
}

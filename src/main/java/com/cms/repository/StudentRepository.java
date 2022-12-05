package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.entity.Student;
import com.cms.entity.Subject;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	
}

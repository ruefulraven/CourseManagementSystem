package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entity.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{

	Subject findBySubjectCode(Integer subjectCode);
}

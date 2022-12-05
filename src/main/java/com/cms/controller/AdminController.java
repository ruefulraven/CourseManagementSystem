package com.cms.controller;

import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cms.dto.StudentDTO;
import com.cms.dto.StudentRequest;
import com.cms.entity.Curriculum;
import com.cms.entity.Student;
import com.cms.entity.Subject;
import com.cms.entity.User;
import com.cms.repository.StudentRepository;
import com.cms.repository.SubjectRepository;
import com.cms.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	@Lazy
	public BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/createCurriculum")
	public String createCurriculum(Curriculum curriculum, Model model){
		return "HALLO";
	}
	
	@GetMapping("/createSubject")
	public String createSubject() {
		return "";
	}
	
	@PostMapping("/encodeSubject")
	public Subject encodeSubject(@RequestBody Subject subject, BindingResult bindingResult) {
		List<String> errorList = new ArrayList<>();
		errorList = (bindingResult.getAllErrors().isEmpty()) ? 
				errorList : updateErrorList(errorList, bindingResult);
		return adminService.encodeSubject(subject);
	}
	
	private List<String> updateErrorList(List<String> errorList, BindingResult bindingResult) {
		bindingResult.getAllErrors().forEach(error -> {
			errorList.add(error.getDefaultMessage());
		});
		return errorList;
	}
	
	@PostMapping("/uploadStudent")
	public Student createStudent(@RequestBody StudentRequest studentRequest, BindingResult bindingResult) {
		List<String> errorList = new ArrayList<>();
		errorList = (bindingResult.getAllErrors().isEmpty()) ? 
				errorList : updateErrorList(errorList, bindingResult);
		Student student = new Student(studentRequest.getStudent());
		
		Subject subject = subjectRepository.findBySubjectCode(studentRequest.getSubject().getSubjectCode());
		
		return adminService.createStudent(student);
	}
	
	@PostMapping("/uploadStudents")
	public String createStudents(MultipartFile file, Model model) {

		try {
			List<Student> students = ExcelController.excelToDBStudent(file.getInputStream());
			model.addAttribute("success", true);
			studentRepository.saveAll(students);
			return "index";
		}catch (IOException e) {
		      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	}
	
	
}

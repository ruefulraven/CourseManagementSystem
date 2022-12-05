package com.cms.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.cms.dto.UserDTO;
import com.cms.entity.User;
import com.cms.repository.UserRepository;
import com.cms.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	@Lazy
	public BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/loginMethod")
	public String loginMethod() {
		return "";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("success", false);
		model.addAttribute("students", retrieveStudentList());
		return "index";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "registration";
	}
	
	@GetMapping("/register")
	public String registerUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Axel");
		userDTO.setLastName("Bajan");
		userDTO.setUserControl("none");
		userDTO.setUserEmail("axelbajan66@gmail.com");
		userDTO.setUserName("abajan");
		userDTO.setUserPassword("20150128");
		userService.save(userDTO);
		return "redirect:/login?success";
	}
	
	@PostMapping("/uploadUsers")
	public String registerUsersExcel(MultipartFile file, Model model) {
		
		try {
			List<User> users = ExcelController.excelToDBUsers(file.getInputStream());
			users.stream().forEach(user -> {
				user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			});
			model.addAttribute("success", true);
			userRepository.saveAll(users);
			return "index";
		}catch (IOException e) {
		      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	}
	
	public List<User> retrieveStudentList() {
		return userService.showStudent();
	}
}

package com.taskprojectmanager.Controller;


import com.taskprojectmanager.Dto.UserDto;
import com.taskprojectmanager.Model.UserEntity;
import com.taskprojectmanager.Repository.UserRepository;
import com.taskprojectmanager.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository repository;
	private final PasswordEncoder encoder;
	
	@GetMapping("/login")
	String showLogin() {
		return "login";
	}
	
	@GetMapping("/registration")
	String showRegistration(Model model) {
		model.addAttribute("user", new UserDto());
		return "registration";
	}
	
	@PostMapping("/register")
	String register(@Valid @ModelAttribute("user") UserDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return "registration";
		}
		
		val user = new UserEntity();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setRoles(Collections.singleton(Role.USER));
		user.setPassword(encoder.encode(dto.getPassword()));
		repository.save(user);
	
		return "redirect:/login";
	}
	
}

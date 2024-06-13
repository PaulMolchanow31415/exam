package com.taskprojectmanager.Controller;

import com.taskprojectmanager.Model.UserEntity;
import com.taskprojectmanager.Repository.UserRepository;
import com.taskprojectmanager.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserRepository repository;
	
	public UserEntity get(UserPrincipal principal) {
		return repository.findById(principal.getId())
				.orElseThrow(() -> new RuntimeException("Пользователь не найден"));
	}
	
	@GetMapping("/all")
	String all(Model model) {
		model.addAttribute("users", repository.findAll());
		return "user-list";
	}
	
	@GetMapping("/delete/{id}")
	String delete(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/user/all";
	}
	
}

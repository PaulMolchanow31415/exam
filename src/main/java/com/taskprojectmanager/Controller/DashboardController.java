package com.taskprojectmanager.Controller;

import com.taskprojectmanager.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {
	
	private final ProjectRepository projectRepository;
	
	@GetMapping("/")
	String index(Model model) {
		model.addAttribute("projects", projectRepository.findAll());
		return "index";
	}
	
}

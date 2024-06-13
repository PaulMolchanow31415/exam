package com.taskprojectmanager.Controller;

import com.taskprojectmanager.Dto.ProjectDto;
import com.taskprojectmanager.Dto.TaskDto;
import com.taskprojectmanager.Model.Project;
import com.taskprojectmanager.Repository.ProjectRepository;
import com.taskprojectmanager.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectRepository repository;
	private final UserController userController;
	
	public Project get(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Проект не найден"));
	}
	
	@GetMapping("/my")
	String all(Model model, @AuthenticationPrincipal UserPrincipal principal) {
		model.addAttribute("projects", userController.get(principal).getProjects());
		return "my-projects";
	}
	
	@GetMapping("/create")
	String showCreate(Model model) {
		model.addAttribute("project", new ProjectDto());
		return "edit-project";
	}
	
	@GetMapping("/edit/{id}")
	String showEdit(Model model, @PathVariable Long id) {
		model.addAttribute("project", ProjectDto.from(get(id)));
		return "edit-project";
	}
	
	@GetMapping("/show/{id}")
	String showProject(Model model, @PathVariable Long id) {
		model.addAttribute("project", ProjectDto.from(get(id)));
		model.addAttribute("task", new TaskDto());
		return "show-project";
	}
	
	@PostMapping("/store")
	String store(
			@AuthenticationPrincipal UserPrincipal principal,
			@Valid @ModelAttribute("project") ProjectDto dto,
			Errors errors
	) {
		if(errors.hasErrors()) {
			return "edit-project";
		}
		
		val project = dto.getId() == null ? new Project() : get(dto.getId());
		
		project.setId(dto.getId());
		project.setName(dto.getName());
		project.setDescription(dto.getDescription());
		project.setOwner(userController.get(principal));
		repository.save(project);
		
		return "redirect:/project/show/" + project.getId();
	}
	
	@GetMapping("/destroy/{id}")
	String destroy(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/project/my";
	}
}

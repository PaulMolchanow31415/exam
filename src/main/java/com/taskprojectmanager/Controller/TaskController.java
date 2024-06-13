package com.taskprojectmanager.Controller;

import com.taskprojectmanager.Dto.TaskDto;
import com.taskprojectmanager.Model.Task;
import com.taskprojectmanager.Repository.TaskRepository;
import com.taskprojectmanager.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
	
	private final TaskRepository repository;
	private final UserController userController;
	private final ProjectController projectController;
	
	public Task get(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Задача не найдена"));
	}
	
	@PostMapping("/store")
	String store(
			@AuthenticationPrincipal UserPrincipal principal,
			@Valid @ModelAttribute("task") TaskDto d,
			@RequestParam Long projectId,
			Errors errors
	) {
		if (errors.hasErrors()) {
			return "redirect:/project/show/" + projectId;
		}
		
		val task = d.getId() == null ? new Task() : get(d.getId());
		
		val project = projectController.get(projectId);
		task.setCreator(userController.get(principal));
		task.setDescription(d.getDescription());
		task.setProject(project);
		repository.save(task);
		
		return "redirect:/project/show/" + project.getId();
	}
}

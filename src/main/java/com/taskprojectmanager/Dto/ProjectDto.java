package com.taskprojectmanager.Dto;

import com.taskprojectmanager.Model.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
	private Long id;
	
	@NotBlank(message = "Заполните название проекта")
	private String name;
	
	@NotBlank(message = "Заполните описание проекта")
	@Size(min = 10, message = "Размер должен быть не меньше 10 символов")
	private String description;
	
	public static ProjectDto from(Project project) {
		return new ProjectDto(project.getId(), project.getName(), project.getDescription());
	}
}

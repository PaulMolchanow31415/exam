package com.taskprojectmanager.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
	private Long id;
	
	@NotBlank(message = "Заполните описание")
	@Size(min = 4, message = "Размер поля должен быть не меньше 4")
	private String description;
}

package com.taskprojectmanager.Dto;

import jakarta.validation.constraints.Email;
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
public class UserDto {
	@UniqueUsername
	@NotBlank(message = "Заполните ваше имя")
	private String username;
	
	@UniqueEmail
	@Email(message = "Введите реальный адрес почты")
	@NotBlank(message = "Заполните ваш email")
	private String email;
	
	@NotBlank(message = "Заполните пароль")
	@Size(min = 6, message = "Минимальный размер пароля 6 символов")
	private String password;
}

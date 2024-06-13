package com.taskprojectmanager.Dto;

import com.taskprojectmanager.Repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	private final UserRepository repository;
	
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if (repository.count() > 0) {
			return repository.findByUsername(s).isEmpty();
		}
		
		return true;
	}
}

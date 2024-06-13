package com.taskprojectmanager;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
	ADMIN("Администратор"),
	USER("Пользователь");
	
	private final String authority;
}

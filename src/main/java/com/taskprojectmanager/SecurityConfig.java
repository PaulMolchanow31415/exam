package com.taskprojectmanager;

import com.taskprojectmanager.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.taskprojectmanager.Role.ADMIN;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserRepository repository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			val user = repository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
			
			val authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
					.toList();
			
			return UserPrincipal.builder()
					.id(user.getId())
					.username(username)
					.password(user.getPassword())
					.authorities(authorities)
					.build();
		};
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		val provider = new DaoAuthenticationProvider(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());
		return new ProviderManager(provider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.securityMatcher("/**")
				.authorizeHttpRequests(registry -> registry
						.requestMatchers("/project/**", "/task/**").authenticated()
						.requestMatchers("/admin/**").hasAuthority(ADMIN.getAuthority())
						.anyRequest().permitAll()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/").permitAll()
				)
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout")
						.permitAll()
				)
				.build();
	}
}

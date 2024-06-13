package com.taskprojectmanager.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Lob
	private String description;
	
	@ManyToOne
	private Project project;
	
	@ManyToOne
	private UserEntity creator;
	
	@ManyToOne
	private UserEntity successor;
}

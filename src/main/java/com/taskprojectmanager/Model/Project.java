package com.taskprojectmanager.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private String name;
	
	@Lob
	private String description;
	
	@ManyToOne
	private UserEntity owner;
	
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "project")
	private List<Task> tasks;
}

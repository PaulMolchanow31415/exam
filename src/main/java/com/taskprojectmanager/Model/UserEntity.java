package com.taskprojectmanager.Model;

import com.taskprojectmanager.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	@ElementCollection(fetch = EAGER)
	private Set<Role> roles;
	@OneToMany(mappedBy = "owner", cascade = ALL, orphanRemoval = true)
	private Set<Project> projects;
	@OneToMany(mappedBy = "creator")
	private Set<Task> tasks;
	@OneToMany(mappedBy = "successor")
	private Set<Task> completedTasks;
}

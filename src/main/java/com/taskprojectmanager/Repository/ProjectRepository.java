package com.taskprojectmanager.Repository;

import com.taskprojectmanager.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

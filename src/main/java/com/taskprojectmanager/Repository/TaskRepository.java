package com.taskprojectmanager.Repository;

import com.taskprojectmanager.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

package com.duikt.todolist.repository;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTodoId(Long todoId);
    List<Task> findAllByDeadline(LocalDateTime deadline);

}

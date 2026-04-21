package com.duikt.todolist.repository;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Todo> findAllByTodoId(Long todoId);

}

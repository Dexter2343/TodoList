package com.duikt.todolist.telegram;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.repository.TaskRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ShowTaskList {
    private final TaskRepository taskRepository;

    public ShowTaskList(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String showTasks() {
        List<Task> tasks = taskRepository.findAll();
        StringBuilder result = new StringBuilder("Твій список завдань:\n\n");

        for (Task t : tasks) {
            String deadline = t.getDeadline() != null
                    ? t.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    : "не вказано";

            String text = String.format(
                    "📌 %s\nОпис: %s\nТермін: %s\n\n",
                    t.getTitle(),
                    t.getDescription(),
                    deadline
            );
            result.append(text);
        }

        return result.toString();
    }

    }

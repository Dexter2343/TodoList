package com.duikt.todolist.telegram;

import com.duikt.todolist.entity.Task;
import com.duikt.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationService {

    private final TaskRepository taskRepository;
    private final NotificationSender notificationSender;


    @Scheduled(cron = "0 0 9 * * *")
//      @Scheduled(fixedRate = 60000)
    public void deadlines(){
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        List<Task> tasks = taskRepository.findAll();

        for(Task t: tasks){
            if(t.getDeadline() == null) continue;
            Long chatId = t.getTodo().getUser().getChatId();
            String text = String.format(
                    "Твоє завдання \"%s\" завершиться %s!",
                    t.getTitle(),
                    t.getDeadline().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            );
            notificationSender.sendNotification(chatId, text);
        }
    }
}

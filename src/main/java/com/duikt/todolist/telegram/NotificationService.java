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

    private boolean deadlineIsend = false;

    private final TaskRepository taskRepository;
    private final NotificationSender notificationSender;
    private final BotLogic logic;


    //    @Scheduled(cron = "0 0 9 * * *")
    @Scheduled(fixedRate = 60000)
    public void deadlines() {
        List<Task> tasks = taskRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Task t : tasks) {
            LocalDateTime deadline = t.getDeadline();
            if (t.getDeadline() == null) continue;
            Long chatId = t.getTodo().getUser().getChatId();
            if (logic.isStarted()) {
                if (deadline.isAfter(now) && logic.isEmailIsConnected())   {
                    String notify = String.format(
                            "Твоє завдання \"%s\" завершиться %s!",
                            t.getTitle(),
                            t.getDeadline().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                    );
                    notificationSender.sendNotification(chatId, notify);
                } else if (!deadline.isAfter(now) && logic.isEmailIsConnected()) {
                    String text = String.format(
                            "Твоє завдання \"%s\" завершило свій термін %s!",
                            t.getTitle(),
                            t.getDeadline().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                    );
                    notificationSender.sendNotification(chatId, text);
                }
            }
        }
    }
}
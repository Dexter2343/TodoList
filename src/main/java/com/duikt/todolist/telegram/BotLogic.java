package com.duikt.todolist.telegram;

import com.duikt.todolist.service.UserService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
@Getter
@Setter
@Data
public class BotLogic implements LongPollingSingleThreadUpdateConsumer{

    private final TelegramClient telegramClient;
    private final UserService userService;
    private final ShowTaskList showTaskList;
    private final NotificationSender notificationSender;

    private boolean isStarted = false;
    private boolean emailIsConnected = false;



    public BotLogic(@Value("${telegram.bot.token}") String token, UserService userService, ShowTaskList showTaskList, NotificationSender notificationSender) {
        this.telegramClient = new OkHttpTelegramClient(token);
        this.userService = userService;
        this.showTaskList = showTaskList;
        this.notificationSender = notificationSender;
    }


    @SneakyThrows
    @Override
    public void consume(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if ("/start".equals(text)) {
            isStarted = true;
            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text("Вітаємо, для прив'язки вашого акаунту надішліть пошту яку зареєстрували")
                    .build();
            telegramClient.execute(message);

        } else if (text != null && text.contains("@")) {
            boolean linked = userService.linkChatId(chatId, text.trim());

            if (linked) {
                emailIsConnected = true;
            }

            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(linked ? "✅ Акаунт прив'язано! Тепер ви будете отримувати сповіщення про дедлайни."
                            : "❌ Email не знайдено. Спробуй ще раз:")
                    .build();
            telegramClient.execute(message);

        } else if ("/tasks".equals(text) && emailIsConnected) {
            String taskList = showTaskList.showTasks();
            notificationSender.sendNotification(chatId, taskList);

        } else {
            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text("Завдань немає або вашу пошту не прив'язано да акаунту!")
                    .build();
            telegramClient.execute(message);
        }
    }
}

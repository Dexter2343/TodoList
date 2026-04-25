package com.duikt.todolist.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class NotificationSender{
    private final TelegramClient telegramClient;

    public NotificationSender(@Value("${telegram.bot.token}") String token) {
        this.telegramClient = new OkHttpTelegramClient(token);
    }

    @SneakyThrows
    public void sendNotification(Long chatId, String text){
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
        telegramClient.execute(message);
    }
}

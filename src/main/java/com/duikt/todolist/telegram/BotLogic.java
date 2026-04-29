package com.duikt.todolist.telegram;

import com.duikt.todolist.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class BotLogic implements LongPollingSingleThreadUpdateConsumer{

    private final TelegramClient telegramClient;
    private final UserService userService;

    public BotLogic(@Value("${telegram.bot.token}") String token, UserService userService) {
        this.telegramClient = new OkHttpTelegramClient(token);
        this.userService = userService;
    }


    @SneakyThrows
    @Override
    public void consume(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if ("/start".equals(text)) {
            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text("Привіт! Надішли свій email для прив'язки акаунту:")
                    .build();
            telegramClient.execute(message);

        } else if (text != null && text.contains("@")) {
            boolean linked = userService.linkChatId(chatId, text.trim());

            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(linked ? "✅ Акаунт прив'язано! Тепер ти будеш отримувати сповіщення про дедлайни."
                            : "❌ Email не знайдено. Спробуй ще раз:")
                    .build();
            telegramClient.execute(message);
        }
    }
}

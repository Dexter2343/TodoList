    package com.duikt.todolist.telegram;

    import lombok.Data;
    import lombok.Getter;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;
    import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
    import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;

    @Component
    @RequiredArgsConstructor
    @Getter
    @Data
    public class BotConfig implements SpringLongPollingBot {
        private final BotLogic echoTest;

        @Value("${telegram.bot.token}")
        private String token;

        @Value("${telegram.bot.username}")
        private String username;

        @Override
        public String getBotToken() {
            return token;
        }

        @Override
        public LongPollingUpdateConsumer getUpdatesConsumer() {
            return echoTest;
        }
    }

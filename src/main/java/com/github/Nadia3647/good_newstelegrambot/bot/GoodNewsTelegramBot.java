package com.github.Nadia3647.good_newstelegrambot.bot;

import com.github.Nadia3647.good_newstelegrambot.command.CommandContainer;
import com.github.Nadia3647.good_newstelegrambot.service.CoinInfoService;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageServiceImpl;
import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.*;

@Component
public class GoodNewsTelegramBot extends TelegramLongPollingBot  {
    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;


    @Autowired
    public GoodNewsTelegramBot(TelegramUserService telegramUserService, CoinInfoService coinInfoService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, coinInfoService);
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                try {
                    commandContainer.retrieveCommand(commandIdentifier).execute(update);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}

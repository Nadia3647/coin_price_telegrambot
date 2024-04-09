package com.github.Nadia3647.good_newstelegrambot.service;


import com.github.Nadia3647.good_newstelegrambot.bot.GoodNewsTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final GoodNewsTelegramBot goodnewsBot;

    @Autowired
    public SendBotMessageServiceImpl(GoodNewsTelegramBot goodnewsBot) {
        this.goodnewsBot = goodnewsBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            goodnewsBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}
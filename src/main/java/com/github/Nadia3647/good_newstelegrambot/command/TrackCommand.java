package com.github.Nadia3647.good_newstelegrambot.command;
import com.github.Nadia3647.good_newstelegrambot.service.*;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserService;

public class TrackCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final CoinInfoService coinInfoService;


    public TrackCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, CoinInfoService coinInfoService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.coinInfoService = coinInfoService;
    }


    @Override
    public void execute(Update update)  {
        Message message = update.getMessage();
        String text = message.getText();
        Long chatId = message.getChatId();

        String[] parts = text.split(" ");

        Integer nominal = Integer.parseInt(parts[1]);
        Integer year = Integer.parseInt(parts[2]);
        String mint = parts[3];
        coinInfoService.save(chatId, nominal, year, mint);

    }
}

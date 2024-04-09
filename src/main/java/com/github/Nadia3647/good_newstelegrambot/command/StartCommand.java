package com.github.Nadia3647.good_newstelegrambot.command;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.TelegramUser;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Чтобы узнать цену монеты воспользуйтесь командой /price, введя после неё цифры номинала года и сокращение монетного двора (ММД или СПМД). " +
            "Пример: /price 10 2001 ММД. Для добавление монеты в избраное воспользуйтесь командой /track, аналогично введя после неё данные о монете. Пример /track 2 2000 СПМД.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
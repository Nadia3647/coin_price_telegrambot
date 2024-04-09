package com.github.Nadia3647.good_newstelegrambot.command;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.*;

public class HelpCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨Дотупные команды✨\n\n"
                    + "%s - начать работу\n"
                    + "%s - приостановить работу\n\n"
                    + "%s - узнать цену монеты на данный момент\n"
                    + "%s - добавить монету в избраное, для получения оповещения в случае изменения её цены\n",
            START.getCommandName(), STOP.getCommandName(), PRICE.getCommandName(),
            TRACK.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}

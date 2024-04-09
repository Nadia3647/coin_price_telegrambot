package command;

import com.github.Nadia3647.good_newstelegrambot.command.Command;
import com.github.Nadia3647.good_newstelegrambot.command.StartCommand;

import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.START;
import static com.github.Nadia3647.good_newstelegrambot.command.StartCommand.START_MESSAGE;

public class StartCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService, telegramUserService);
    }
}

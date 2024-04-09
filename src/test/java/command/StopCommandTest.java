package command;

import com.github.Nadia3647.good_newstelegrambot.command.Command;
import com.github.Nadia3647.good_newstelegrambot.command.StopCommand;

import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.STOP;
import static com.github.Nadia3647.good_newstelegrambot.command.StopCommand.STOP_MESSAGE;

public class StopCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}

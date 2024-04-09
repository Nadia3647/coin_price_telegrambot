package command;

import com.github.Nadia3647.good_newstelegrambot.command.Command;
import com.github.Nadia3647.good_newstelegrambot.command.HelpCommand;

import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.HELP;
import static com.github.Nadia3647.good_newstelegrambot.command.HelpCommand.HELP_MESSAGE;

public class HelpCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}

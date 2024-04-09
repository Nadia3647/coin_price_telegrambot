package command;
import org.junit.jupiter.api.DisplayName;
import com.github.Nadia3647.good_newstelegrambot.command.Command;
import com.github.Nadia3647.good_newstelegrambot.command.NoCommand;

import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.NO;
import static com.github.Nadia3647.good_newstelegrambot.command.NoCommand.NO_MESSAGE;

public class NoCommandTest extends AbstractCommandTest{




    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}

package command;

import com.github.Nadia3647.good_newstelegrambot.command.Command;
import com.github.Nadia3647.good_newstelegrambot.command.CommandContainer;
import com.github.Nadia3647.good_newstelegrambot.command.CommandName;
import com.github.Nadia3647.good_newstelegrambot.command.UnknownCommand;
import com.github.Nadia3647.good_newstelegrambot.service.CoinInfoService;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        CoinInfoService coinInfoService= Mockito.mock(CoinInfoService.class);
        commandContainer = new CommandContainer(sendBotMessageService,
                telegramUserService, coinInfoService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.findCommand(commandName.getCommandName(), "username");
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";

        //when
        Command command = commandContainer.findCommand(unknownCommand, "username");

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}

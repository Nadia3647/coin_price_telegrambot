package com.github.Nadia3647.good_newstelegrambot.command;
import com.github.Nadia3647.good_newstelegrambot.job.CoinPrice;
import com.github.Nadia3647.good_newstelegrambot.service.CoinInfoService;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;
import static com.github.Nadia3647.good_newstelegrambot.command.CommandName.*;



public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, CoinInfoService сoinInfoService) {
        CoinPrice coinPrice = new CoinPrice();
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(PRICE.getCommandName(), new PriceCommand(sendBotMessageService, coinPrice))
                .put(TRACK.getCommandName(), new TrackCommand(sendBotMessageService, telegramUserService, сoinInfoService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

    public Command findCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        return orDefault;
    }
}

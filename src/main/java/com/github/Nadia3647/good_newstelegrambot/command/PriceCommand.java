package com.github.Nadia3647.good_newstelegrambot.command;
import com.github.Nadia3647.good_newstelegrambot.job.CoinPrice;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.IOException;


public class PriceCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final CoinPrice coinPrice;

    public PriceCommand(SendBotMessageService sendBotMessageService, CoinPrice coinPrice) {
        this.sendBotMessageService = sendBotMessageService;
        this.coinPrice = coinPrice;
    }

    @Override
    public void execute(Update update) throws IOException {

        Message message = update.getMessage();
        String text = message.getText();

        String[] parts = text.split(" ");

        String nominal = parts[1];
        String year = parts[2];
        String mint = parts[3];
        String price = coinPrice.getCoinPrice(nominal, year, mint);
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),"Цена монеты с номиналом " + nominal + ", годом " + year + " и монетным двором " + mint + " составляет: " + price);

    }
}
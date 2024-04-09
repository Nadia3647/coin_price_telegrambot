package service;

import com.github.Nadia3647.good_newstelegrambot.bot.GoodNewsTelegramBot;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private GoodNewsTelegramBot goodNewsTelegramBot;

    @BeforeEach
    public void init() {
        goodNewsTelegramBot = Mockito.mock(GoodNewsTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(goodNewsTelegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "123L";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(goodNewsTelegramBot).execute(sendMessage);
    }
}


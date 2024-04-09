package service;

import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserServiceImpl;
import com.github.Nadia3647.good_newstelegrambot.repository.TelegramUserRepository;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

public class TelegramUserServiceTest {
    private TelegramUserRepository telegramUserRepository;
    private TelegramUserServiceImpl telegramUserService;

    @BeforeEach
    public void init() {
        telegramUserRepository = Mockito.mock(TelegramUserRepository.class);
        telegramUserService = new TelegramUserServiceImpl(telegramUserRepository);
    }

    @Test
    public void shouldProperlySave() {
        //given
        TelegramUser telegramUser = new TelegramUser();


        //when
        telegramUserService.save(telegramUser);

        //then
        Mockito.verify(telegramUserRepository).save(telegramUser);
    }

    @Test
    public void shouldProperlyRetrieveAllActiveUsers() {
        //given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setActive(true);
        Mockito.when(telegramUserRepository.findAllByActiveTrue()).thenReturn(Arrays.asList(telegramUser));

        //when
        telegramUserService.retrieveAllActiveUsers();

        //then
        Mockito.verify(telegramUserRepository).findAllByActiveTrue();
    }

    @Test
    public void shouldProperlyFindByChatId() {
        //given
        String chatId = "123L";
        Mockito.when(telegramUserRepository.findById(chatId)).thenReturn(Optional.of(new TelegramUser()));

        //when
        telegramUserService.findByChatId(chatId);

        //then
        Mockito.verify(telegramUserRepository).findById(chatId);
    }
}

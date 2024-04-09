package service;

import com.github.Nadia3647.good_newstelegrambot.job.CoinPrice;
import com.github.Nadia3647.good_newstelegrambot.repository.CoinInfoRepository;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.CoinInfo;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.TelegramUser;
import com.github.Nadia3647.good_newstelegrambot.service.CoinInfoServiseImpl;
import com.github.Nadia3647.good_newstelegrambot.service.TelegramUserService;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
public class CoinInfoServiceTest {
    private CoinInfoServiseImpl coinInfoServise;

    private CoinInfoRepository coinInfoRepository;

    private TelegramUserService telegramUserService;

    private CoinPrice coinPrice;

    @BeforeEach
    public void init() {

        coinInfoRepository = Mockito.mock(CoinInfoRepository.class);
        telegramUserService = Mockito.mock(TelegramUserService.class);
        coinPrice = Mockito.mock(CoinPrice.class);
        coinInfoServise = new CoinInfoServiseImpl(coinInfoRepository, telegramUserService, coinPrice);

    }

    @Test
    public void testSave() {

        Long chatId = 12345L;
        Integer nominal = 100;
        Integer year = 2020;
        String mint = "Mint";

        when(telegramUserService.findByChatId(chatId.toString())).thenReturn(Optional.of(new TelegramUser()));
        when(coinInfoRepository.findByNominalAndYearAndMint(nominal, year, mint)).thenReturn(Optional.empty());
        when(coinPrice.getCoinPrice(nominal.toString(), year.toString(), mint)).thenReturn("500");


        CoinInfo result = coinInfoServise.save(chatId, nominal, year, mint);

        assertEquals(nominal, result.getNominal());
        assertEquals(year, result.getYear());
        assertEquals(mint, result.getMint());
        assertEquals(Integer.valueOf(500), result.getPrice());
    }
}

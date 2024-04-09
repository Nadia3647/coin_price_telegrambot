package service;

import com.github.Nadia3647.good_newstelegrambot.service.FindNewCoinPriceServiceImpl;
import com.github.Nadia3647.good_newstelegrambot.service.CoinInfoService;
import com.github.Nadia3647.good_newstelegrambot.job.CoinPrice;
import com.github.Nadia3647.good_newstelegrambot.service.SendBotMessageService;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.CoinInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
public class FindNewCoinPriceServiceTest {
    private CoinInfoService coinInfoService;
    private CoinPrice coinPrice;
    private SendBotMessageService sendMessageService;
    private FindNewCoinPriceServiceImpl findNewCoinPriceService;

    @BeforeEach
    public void init() {
        coinInfoService = Mockito.mock(CoinInfoService.class);
        coinPrice = Mockito.mock(CoinPrice.class);
        sendMessageService = Mockito.mock(SendBotMessageService.class);

        findNewCoinPriceService = new FindNewCoinPriceServiceImpl(coinInfoService, coinPrice, sendMessageService);
    }

    @Test
    public void shouldProperlyFindNewPrices() {
        //given
        CoinInfo coin = new CoinInfo();
        coin.setPrice(100);
        Mockito.when(coinInfoService.findAll()).thenReturn(Arrays.asList(coin));
        Mockito.when(coinPrice.getCoinPrice(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("200");

        //when
        findNewCoinPriceService.findNewPrices();

        //then
        Mockito.verify(coinInfoService).updateCoinPrice(Mockito.any(), Mockito.anyInt());
    }
}

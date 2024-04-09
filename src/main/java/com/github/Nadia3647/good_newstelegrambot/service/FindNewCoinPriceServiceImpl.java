package com.github.Nadia3647.good_newstelegrambot.service;

import com.github.Nadia3647.good_newstelegrambot.job.CoinPrice;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.CoinInfo;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindNewCoinPriceServiceImpl implements FindNewCoinPriceService{
    private final CoinInfoService coinInfoService;
    private final CoinPrice coinPrice;
    private final SendBotMessageService sendMessageService;

    @Autowired
    public FindNewCoinPriceServiceImpl(CoinInfoService coinInfoService,
                                       CoinPrice coinPrice,
                                       SendBotMessageService sendMessageService) {

        this.coinInfoService = coinInfoService;
        this.coinPrice = coinPrice;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void findNewPrices() {
        coinInfoService.findAll().forEach(coin -> {
            String currentPrice = coinPrice.getCoinPrice(coin.getNominal().toString(), coin.getYear().toString(), coin.getMint());
            int oldPrice = coin.getPrice();

            int newPrice = Integer.parseInt((currentPrice));

            if (Math.abs(newPrice - oldPrice) / oldPrice > 10.0) {
                notifySubscribersAboutNewPrice(coin, currentPrice);
            }
            coin.setPrice(Integer.parseInt(currentPrice));
            coinInfoService.updateCoinPrice(coin.getId(), Integer.parseInt(currentPrice));
        });
    }

    private void notifySubscribersAboutNewPrice(CoinInfo coin, String newPrice) {
        String message = String.format("Цена монеты номиналом  %s  %d года %s монетного двора изменилась. Новая цена: %s",
                coin.getNominal(), coin.getYear(), coin.getMint(), newPrice);
        coin.getUsers().stream().filter(TelegramUser::isActive).forEach(it -> sendMessageService.sendMessage(it.getChatId(), message));
    }
}

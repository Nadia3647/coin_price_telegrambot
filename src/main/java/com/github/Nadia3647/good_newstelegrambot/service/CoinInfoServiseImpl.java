package com.github.Nadia3647.good_newstelegrambot.service;

import com.github.Nadia3647.good_newstelegrambot.job.CoinPrice;
import com.github.Nadia3647.good_newstelegrambot.repository.CoinInfoRepository;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.CoinInfo;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.TelegramUser;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CoinInfoServiseImpl implements CoinInfoService {
    private final CoinInfoRepository coinInfoRepository;
    private final TelegramUserService telegramUserService;
    private final CoinPrice coinPrice;

    @Autowired
    public CoinInfoServiseImpl(CoinInfoRepository coinInfoRepository, TelegramUserService telegramUserService, CoinPrice coinPrice) {
        this.coinInfoRepository = coinInfoRepository;
        this.telegramUserService = telegramUserService;
        this.coinPrice = coinPrice;

    }

    public CoinInfo save(Long chatId, Integer nominal, Integer year, String mint) {

        TelegramUser telegramUser = telegramUserService.findByChatId(chatId.toString()).orElseThrow(NotFoundException::new);
        CoinInfo coinInfo;
        Optional<CoinInfo> coinInfoFromDB = coinInfoRepository.findByNominalAndYearAndMint(nominal, year, mint);

        if (coinInfoFromDB.isPresent()) {
            coinInfo = coinInfoFromDB.get();
            if (!coinInfo.getUsers().stream().anyMatch(it -> it.getChatId().equals(chatId))) {
                coinInfo.addUser(telegramUser);
            }
        } else {
            coinInfo = new CoinInfo();
            coinInfo.addUser(telegramUser);
            coinInfo.setNominal(nominal);
            coinInfo.setYear(year);
            coinInfo.setMint(mint);
            coinInfo.setPrice(Integer.parseInt(coinPrice.getCoinPrice(nominal.toString(),year.toString(),mint)));
        }
        return coinInfoRepository.save(coinInfo);
    }
    @Override
    public void updateCoinPrice(Integer id, Integer newPrice) {
        CoinInfo coin = coinInfoRepository.findById(id).orElseThrow(NotFoundException::new);
        coin.setPrice(newPrice);
        coinInfoRepository.save(coin);
    }
    @Override
    public Optional<CoinInfo> findById(Integer id) {
        return coinInfoRepository.findById(id);
    }
   @Override
    public List<CoinInfo> findAll() {
        return coinInfoRepository.findAll();
    }
}
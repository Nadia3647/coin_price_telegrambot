package com.github.Nadia3647.good_newstelegrambot.service;

import com.github.Nadia3647.good_newstelegrambot.repository.entity.CoinInfo;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface CoinInfoService {
    CoinInfo save(Long chatId, Integer nominal, Integer year, String mint);
    void updateCoinPrice(Integer id, Integer newPrice);
    public Optional<CoinInfo> findById(Integer id);

    public List<CoinInfo> findAll();

}

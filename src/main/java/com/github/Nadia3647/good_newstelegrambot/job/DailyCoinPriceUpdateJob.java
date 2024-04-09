package com.github.Nadia3647.good_newstelegrambot.job;
import com.github.Nadia3647.good_newstelegrambot.service.FindNewCoinPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Slf4j
@Component
public class DailyCoinPriceUpdateJob {

    private final FindNewCoinPriceService findNewCoinPriceService;

    @Autowired
    public DailyCoinPriceUpdateJob (FindNewCoinPriceService findNewCoinPriceService) {
        this.findNewCoinPriceService = findNewCoinPriceService;
    }

    @Scheduled(fixedRateString = "${bot.recountNewCoinPriceFixedRate}")
    public void findNewCoinPrices() {

        LocalDateTime start = LocalDateTime.now();
        log.info("Find new article job started.");

        findNewCoinPriceService.findNewPrices();
        LocalDateTime end = LocalDateTime.now();

        log.info("Find new articles job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));

    }
}

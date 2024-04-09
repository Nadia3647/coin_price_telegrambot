package com.github.Nadia3647.good_newstelegrambot.repository;
import com.github.Nadia3647.good_newstelegrambot.repository.entity.CoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CoinInfoRepository extends JpaRepository<CoinInfo, Integer> {
    Optional<CoinInfo> findByNominalAndYearAndMint(Integer nominal, Integer year, String mint);
}
